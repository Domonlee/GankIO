package domon.cn.gankio.presenter.impl;

import android.view.View;

import com.socks.library.KLog;

import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.data.GankHistoryData;
import domon.cn.gankio.network.RetrofitHttpUtil;
import domon.cn.gankio.presenter.IHomePresenter;
import domon.cn.gankio.utils.SharedPreferenceUtil;
import domon.cn.gankio.view.IHomeView;
import rx.Subscriber;

/**
 * Created by Domon on 16-8-11.
 */
public class HomePresenterImpl implements IHomePresenter {
    private IHomeView iHomeView;
    private Subscriber<GankHistoryData> mHistorySubscriber;
    private Subscriber<GankContentData> mContentDataSubscriber;

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

        mContentDataSubscriber = new Subscriber<GankContentData>() {
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
        };

        RetrofitHttpUtil.getInstance().getRxGankInfoData(mContentDataSubscriber,getDate());
    }

    @Override
    public void reqDateInfo() {
        iHomeView.setProgressDialogVisibility(View.VISIBLE);

        mHistorySubscriber = new Subscriber<GankHistoryData>() {
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
                SharedPreferenceUtil.setStrListValue("gankDateInfoList", gankHistoryData.getResults());
            }
        };

        RetrofitHttpUtil.getInstance().getRxGankHistoryDare(mHistorySubscriber);
    }
}