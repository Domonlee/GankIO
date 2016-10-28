package domon.cn.gankio.presenter.impl;

import android.content.Context;

import domon.cn.gankio.contract.HomeContract;
import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.network.RetrofitHttpUtil;
import domon.cn.gankio.utils.SharedPreferenceUtil;
import domon.cn.gankio.utils.progress.ProgressSubscriber;
import domon.cn.gankio.utils.progress.SubscriberOnNextListener;

/**
 * Created by Domon on 16-8-11.
 */
public class HomePresenterImpl implements HomeContract.Presenter {
    private HomeContract.View iHomeView;
    private Context mContext;

    public HomePresenterImpl(HomeContract.View iHomeView, Context context) {
        this.iHomeView = iHomeView;
        this.mContext = context;

        iHomeView.setPresenter(this);
    }

    private String getDate() {
        if (SharedPreferenceUtil.getStrListValue("gankDateInfoList") != null) {
            return SharedPreferenceUtil.getStrListValue("gankDateInfoList").get(0).replace("-", "/");
        }
        return null;
    }

    @Override
    public void reqHomeGankData() {
        ProgressSubscriber<GankContentData> progressSubscriber = new ProgressSubscriber<>(new SubscriberOnNextListener<GankContentData>() {
            @Override
            public void onNext(GankContentData gankContentData) {
                iHomeView.setData(gankContentData);
            }
        }, mContext);
        RetrofitHttpUtil.getInstance().getRxGankInfoData(progressSubscriber, getDate());
    }

    @Override
    public void start() {
        reqHomeGankData();
    }
}