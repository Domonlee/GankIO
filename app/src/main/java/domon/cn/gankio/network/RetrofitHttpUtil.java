package domon.cn.gankio.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Domon on 16-9-14.
 */
public class RetrofitHttpUtil {
    public static final String GankBaseUrl = "http://www.gank.io/api/";
    public static final String JianDanBaseUrl = "http://pho.orrindeng.com/pho/getpho/";
    private static RetrofitHttpUtil mRetrofitHttpUtil;

    private String mBaseUrl;
    private Retrofit mRetrofit = null;

    public void setmBaseUrl(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    public String getmBaseUrl() {
        if (mBaseUrl == null) {
            return GankBaseUrl;
        }
        return mBaseUrl;
    }

    public static RetrofitHttpUtil getInstance() {
        synchronized (RetrofitHttpUtil.class) {
            if (mRetrofitHttpUtil == null) {
                mRetrofitHttpUtil = new RetrofitHttpUtil();
            }
        }
        return mRetrofitHttpUtil;
    }

    public Retrofit getmRetrofit() {
        return mRetrofit;
    }

    public void setmRetrofit(Retrofit mRetrofit) {
        synchronized (this) {
            this.mRetrofit = mRetrofit;
        }
    }

    public <T> T dataService(Class<T> cls) {
        T service = mRetrofit.create(cls);
        return service;
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(getmBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public RetrofitHttpUtil() {
        if (mRetrofit == null) {
            initRetrofit();
        }
    }
}
