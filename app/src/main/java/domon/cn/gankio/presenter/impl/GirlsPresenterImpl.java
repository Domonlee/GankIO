package domon.cn.gankio.presenter.impl;

import android.view.View;

import com.socks.library.KLog;

import domon.cn.gankio.data.GankGirlsData;
import domon.cn.gankio.network.RetrofitHttpUtil;
import domon.cn.gankio.network.rxAPIs;
import domon.cn.gankio.presenter.IGirlsPresenter;
import domon.cn.gankio.view.IGirlsView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Domon on 16-8-12.
 */
public class GirlsPresenterImpl implements IGirlsPresenter {
    private IGirlsView mIGirlsView;

    public GirlsPresenterImpl(IGirlsView iGirlsView) {
        this.mIGirlsView = iGirlsView;
    }

    @Override
    public void reqGrilsGankData(String index, String count) {
        mIGirlsView.setProgressBarVisibility(View.VISIBLE);


        RetrofitHttpUtil.getInstance().dataService(rxAPIs.class)
                .getRxAllGankGirlData(count, index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankGirlsData>() {
                    @Override
                    public void onCompleted() {
                        mIGirlsView.setProgressBarVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mIGirlsView.setProgressBarVisibility(View.GONE);
                        KLog.e(e);
                    }

                    @Override
                    public void onNext(GankGirlsData gankGirlsData) {
                        mIGirlsView.setData(gankGirlsData);
                    }
                });
    }
}
