package domon.cn.gankio.presenter.impl;

import android.view.View;

import com.socks.library.KLog;

import domon.cn.gankio.data.JiandanGirlsData;
import domon.cn.gankio.network.rxAPIs;
import domon.cn.gankio.presenter.IJiandanPresenter;
import domon.cn.gankio.view.IJiandanView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Domon on 16-8-30.
 */
public class JiandanPresenterImpl implements IJiandanPresenter {

    private IJiandanView mIJiandanView;

    public JiandanPresenterImpl(IJiandanView mIJiandanView) {
        this.mIJiandanView = mIJiandanView;
    }

    @Override
    public void reqJiandanGirls(String index, String count) {
        mIJiandanView.setProgressBarVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(rxAPIs.JianDanBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        rxAPIs rxAPIs = retrofit.create(rxAPIs.class);

        rxAPIs.getRxJianDanGirlsDate(index, count)
                .filter(new Func1<JiandanGirlsData, Boolean>() {
                    @Override
                    public Boolean call(JiandanGirlsData jiandanGirlsData) {
                        if (jiandanGirlsData.getCode() == 0) {
                            return true;
                        }
                        return false;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JiandanGirlsData>() {
                    @Override
                    public void onCompleted() {
                        mIJiandanView.setProgressBarVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e);
                        mIJiandanView.setProgressBarVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(JiandanGirlsData jiandanGirlsData) {
                        mIJiandanView.setData(jiandanGirlsData.getResults());
                    }
                });
    }
}
