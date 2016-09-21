package domon.cn.gankio.presenter.impl;

import android.content.Context;

import domon.cn.gankio.data.GankGirlsData;
import domon.cn.gankio.network.RetrofitHttpUtil;
import domon.cn.gankio.presenter.IGirlsPresenter;
import domon.cn.gankio.utils.progress.ProgressSubscriber;
import domon.cn.gankio.utils.progress.SubscriberOnNextListener;
import domon.cn.gankio.view.IGirlsView;

/**
 * Created by Domon on 16-8-12.
 */
public class GirlsPresenterImpl implements IGirlsPresenter {
    private IGirlsView mIGirlsView;
    private Context mContext;

    public GirlsPresenterImpl(IGirlsView iGirlsView, Context context) {
        this.mIGirlsView = iGirlsView;
        this.mContext = context;
    }

    @Override
    public void reqGrilsGankData(String index, String count) {
        ProgressSubscriber<GankGirlsData> progressSubscriber = new ProgressSubscriber<>(new SubscriberOnNextListener<GankGirlsData>() {
            @Override
            public void onNext(GankGirlsData gankGirlsData) {
                mIGirlsView.setData(gankGirlsData);
            }
        }, mContext);

        RetrofitHttpUtil.getInstance().getRxGankGrilsData(progressSubscriber, count, index);
    }
}
