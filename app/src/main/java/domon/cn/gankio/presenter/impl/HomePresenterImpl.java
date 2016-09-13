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
import retrofit2.Call;
import retrofit2.Callback;
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestActivity.GankBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        rxAPIs rxAPIs = retrofit.create(domon.cn.gankio.network.rxAPIs.class);

        Call<GankContentData> model = rxAPIs.getRxGankInfoData(getDate());

        model.enqueue(new Callback<GankContentData>() {
            @Override
            public void onResponse(Call<GankContentData> call, retrofit2.Response<GankContentData> response) {
                KLog.e("reqHomeGankData");
                iHomeView.setProgressDialogVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    iHomeView.setProgressDialogVisibility(View.GONE);
                    iHomeView.setData(response.body());
                }
            }

            @Override
            public void onFailure(Call<GankContentData> call, Throwable t) {
                KLog.e();
                iHomeView.setProgressDialogVisibility(View.GONE);
            }
        });
    }

    @Override
    public void reqDateInfo() {

        Retrofit retorfit = new Retrofit.Builder()
                .baseUrl(TestActivity.GankBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        rxAPIs rxAPIs = retorfit.create(domon.cn.gankio.network.rxAPIs.class);

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
                    public void onStart() {
                        iHomeView.setProgressDialogVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCompleted() {
                        KLog.e();
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
                        KLog.e("setDate");
                        SharedPreferenceUtil.setStrListValue("gankDateInfoList", gankHistoryData.getResults());
                    }
                });
    }
}
