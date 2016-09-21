package domon.cn.gankio.presenter.impl;

import android.content.Context;

import domon.cn.gankio.data.GankCategoryData;
import domon.cn.gankio.network.RetrofitHttpUtil;
import domon.cn.gankio.network.rxAPIs;
import domon.cn.gankio.presenter.ICategoryPresenter;
import domon.cn.gankio.utils.progress.ProgressSubscriber;
import domon.cn.gankio.utils.progress.SubscriberOnNextListener;
import domon.cn.gankio.view.ICategoryView;

/**
 * Created by Domon on 16-8-22.
 */
public class CategoryPresenterImpl implements ICategoryPresenter {
    private ICategoryView mCategoryView;
    private Context mContext;

    public CategoryPresenterImpl(ICategoryView mCategoryView, Context context) {
        this.mCategoryView = mCategoryView;
        this.mContext = context;
    }

    @Override
    public void reqCategoryData(int type, String index, String count) {
        ProgressSubscriber<GankCategoryData> progressSubscriber = new ProgressSubscriber<>(
                new SubscriberOnNextListener<GankCategoryData>() {
                    @Override
                    public void onNext(GankCategoryData gankCategoryData) {
                        mCategoryView.setCategoryDate(gankCategoryData.getResults());
                    }
                }, mContext);

        RetrofitHttpUtil.getInstance().getRxGankCategoryData(progressSubscriber, rxAPIs.GankCategory[type], count, index);
    }
}
