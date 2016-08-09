package domon.cn.gankio.network;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Domon on 16-8-9.
 */
public class OkHttpHelper {
    public static final int METHOD_GET = 0;
    public static final int METHOD_POST = 1;
    private static OkHttpHelper mOkHttpHelperInstance;
    private static OkHttpClient mClientInstance;
    private Handler mHandler;
    private Gson mGson;

    private OkHttpHelper() {
        mClientInstance = new OkHttpClient();

        mClientInstance.setConnectTimeout(10, TimeUnit.SECONDS);
        mClientInstance.setReadTimeout(10, TimeUnit.SECONDS);
        mClientInstance.setWriteTimeout(30, TimeUnit.SECONDS);
        mGson = new Gson();

        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * get Instance
     */
    public static OkHttpHelper getInstance() {
        if (mOkHttpHelperInstance == null) {
            synchronized (OkHttpHelper.class) {
                if (mClientInstance == null) {
                    mOkHttpHelperInstance = new OkHttpHelper();
                }
            }
        }
        return mOkHttpHelperInstance;
    }

    public void request(final Request request, final BaseCallback callback) {
        //请求之前做的事情
        callback.onRequestBefore();

        mClientInstance.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callbackFailure(request, callback, e);

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String resStr = response.body().string();

                    if (callback.mType == String.class) {
                        //若返回类型是String
                        callbackSuccess(response, resStr, callback);
                    } else {
                        try {
                            Object o = mGson.fromJson(resStr, callback.mType);
                            callbackSuccess(response, o, callback);
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                            callbackError(response, callback, e);
                        }
                    }
                } else {
                    callbackError(response, callback, null);
                }
            }
        });
    }

    private void callbackSuccess(final Response response, final Object o, final BaseCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response, o);
            }
        });
    }

    private void callbackError(final Response response, final BaseCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(response, response.code(), e);
            }
        });
    }

    private void callbackFailure(final Request request, final BaseCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(request, e);
            }
        });
    }

    public void get(String url, BaseCallback baseCallback) {
        Request request = buildRequest(url, null, OkHttpHelper.METHOD_GET);
        request(request, baseCallback);
    }

    public void post(String url, Map<String, String> params, BaseCallback baseCallback) {
        Request request = buildRequest(url, params, OkHttpHelper.METHOD_POST);
        request(request, baseCallback);
    }

    private Request buildRequest(String url, Map<String, String> params, int type) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (type == OkHttpHelper.METHOD_GET) {
            builder.get();
        } else if (type == METHOD_POST) {
            builder.post(buildRequestBody(params));
        }
        return builder.build();
    }

    private RequestBody buildRequestBody(Map<String, String> params) {
        FormEncodingBuilder builder = new FormEncodingBuilder();

        if (params == null) {
            for (Map.Entry<String, String> entity : params.entrySet()) {
                builder.add(entity.getKey(), entity.getValue());
            }
        }
        return builder.build();
    }
}
