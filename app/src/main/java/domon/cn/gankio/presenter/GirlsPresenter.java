package domon.cn.gankio.presenter;

import android.content.Context;

import domon.cn.gankio.contract.GirlsContract;
import domon.cn.gankio.data.GankGirlsData;
import domon.cn.gankio.network.RetrofitHttpUtil;
import domon.cn.gankio.utils.progress.ProgressSubscriber;
import domon.cn.gankio.utils.progress.SubscriberOnNextListener;

/**
 * Created by Domon on 16-8-12.
 */
public class GirlsPresenter implements GirlsContract.Presenter {
    private GirlsContract.View mIGirlsView;
    private Context mContext;

    public GirlsPresenter(GirlsContract.View iGirlsView, Context context) {
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

    @Override
    public void start() {

    }
}
