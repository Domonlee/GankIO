package domon.cn.gankio.network;

import java.util.concurrent.TimeUnit;

import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.data.GankHistoryData;
import domon.cn.gankio.utils.SharedPreferenceUtil;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Domon on 16-9-14.
 */
public class RetrofitHttpUtil {
    public static final String GankBaseUrl = "http://www.gank.io/api/";
    public static final String JianDanBaseUrl = "http://pho.orrindeng.com/pho/getpho/";
    public static final int DEFAULT_TIMEOUT = 5;

    private OkHttpClient.Builder mOKHttpClientBuilder;
    private Retrofit mRetrofit = null;
    private rxAPIs rxAPIs;

    private static class SingletonHolder {
        public static final RetrofitHttpUtil INSTANCE = new RetrofitHttpUtil();
    }

    public static RetrofitHttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <T> T dataService(Class<T> cls) {
        T service = mRetrofit.create(cls);
        return service;
    }

    private void initOKHttp() {
        mOKHttpClientBuilder = new OkHttpClient.Builder();
        mOKHttpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    private RetrofitHttpUtil() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(GankBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        rxAPIs = mRetrofit.create(rxAPIs.class);
    }

    public void getRxGankHistoryDare(Subscriber<GankHistoryData> subscriber) {
        Observable observable = rxAPIs.getRxGankHistoryDate()
                .filter(new Func1<GankHistoryData, Boolean>() {
                    @Override
                    public Boolean call(GankHistoryData gankHistoryData) {
                        if (!gankHistoryData.getResults().equals(SharedPreferenceUtil.getStrListValue("gankDateInfoList"))) {
                            return true;
                        }
                        return false;
                    }
                });
        toSubscribe(observable, subscriber);
    }

    public void getRxGankInfoData(Subscriber<GankContentData> subscriber, String date) {
        Observable observable = rxAPIs.getRxGankInfoData(date);
        toSubscribe(observable, subscriber);
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}