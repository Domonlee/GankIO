package domon.cn.gankio.presenter;

import android.content.Context;

import domon.cn.gankio.contract.CategoryContract;
import domon.cn.gankio.data.GankCategoryData;
import domon.cn.gankio.network.RetrofitHttpUtil;
import domon.cn.gankio.network.rxAPIs;
import domon.cn.gankio.utils.progress.ProgressSubscriber;
import domon.cn.gankio.utils.progress.SubscriberOnNextListener;

/**
 * Created by Domon on 16-8-22.
 */
public class CategoryPresenter implements CategoryContract.Presenter {
    private CategoryContract.View mCategoryView;
    private Context mContext;

    public CategoryPresenter(CategoryContract.View mCategoryView, Context context) {
        this.mCategoryView = mCategoryView;
        this.mContext = context;

        mCategoryView.setPresenter(this);
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

    @Override
    public void start() {

    }
}
