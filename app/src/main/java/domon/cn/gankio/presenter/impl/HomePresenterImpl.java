package domon.cn.gankio.presenter.impl;

import android.view.View;

import com.socks.library.KLog;

import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.data.GankHistoryData;
import domon.cn.gankio.network.rxAPIs;
import domon.cn.gankio.presenter.IHomePresenter;
import domon.cn.gankio.ui.activity.TestActivity;
import domon.cn.gankio.utils.SharedPreferenceUtil;
import domon.cn.gankio.view.IHomeView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Domon on 16-8-11.
 */
public class HomePresenterImpl implements IHomePresenter {
    private IHomeView iHomeView;

    public HomePresenterImpl(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
    }

    private String getDate() {
        if (SharedPreferenceUtil.getStrListValue("gankDateInfoList") != null) {
            return SharedPreferenceUtil.getStrListValue("gankDateInfoList").get(0).replace("-", "/");
        }
        return null;
    }

    @Override
    public void reqHomeGankData() {
        iHomeView.setProgressDialogVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestActivity.GankBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        rxAPIs rxAPIs = retrofit.create(rxAPIs.class);

        rxAPIs.getRxGankInfoData(getDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankContentData>() {
                    @Override
                    public void onCompleted() {
                        iHomeView.setProgressDialogVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e);
                        iHomeView.setProgressDialogVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(GankContentData gankContentData) {
                        iHomeView.setData(gankContentData);
                    }
                });
    }

    @Override
    public void reqDateInfo() {
        iHomeView.setProgressDialogVisibility(View.VISIBLE);

        Retrofit retorfit = new Retrofit.Builder()
                .baseUrl(TestActivity.GankBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        rxAPIs rxAPIs = retorfit.create(rxAPIs.class);

        rxAPIs.getRxGankHistoryDate()
                .filter(new Func1<GankHistoryData, Boolean>() {
                    @Override
                    public Boolean call(GankHistoryData gankHistoryData) {
                        if (!gankHistoryData.getResults().equals(SharedPreferenceUtil.getStrListValue("gankDateInfoList"))) {
                            return true;
                        }
                        return false;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankHistoryData>() {
                    @Override
                    public void onCompleted() {
                        iHomeView.getToadyGank();
                        iHomeView.setProgressDialogVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e);
                        iHomeView.setProgressDialogVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(GankHistoryData gankHistoryData) {
                        SharedPreferenceUtil.setStrListValue("gankDateInfoList", gankHistoryData.getResults());
                    }
                });
    }
}