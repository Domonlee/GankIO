package domon.cn.gankio.presenter.impl;

import android.view.View;

import com.socks.library.KLog;

import domon.cn.gankio.data.GankCategoryData;
import domon.cn.gankio.network.RetrofitHttpUtil;
import domon.cn.gankio.network.rxAPIs;
import domon.cn.gankio.presenter.ICategoryPresenter;
import domon.cn.gankio.view.ICategoryView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Domon on 16-8-22.
 */
public class CategoryPresenterImpl implements ICategoryPresenter {
    private ICategoryView mCategoryView;

    public CategoryPresenterImpl(ICategoryView mCategoryView) {
        this.mCategoryView = mCategoryView;
    }

    @Override
    public void reqCategoryData(int type, String index, String count) {
        mCategoryView.setProgressDialogVisibility(View.VISIBLE);

        // FIXME: 16-9-14 多次请求
        RetrofitHttpUtil.getInstance().dataService(rxAPIs.class)
                .getRxAllGankData(rxAPIs.GankCategory[type], count, index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankCategoryData>() {
                    @Override
                    public void onCompleted() {
                        mCategoryView.setProgressDialogVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e);
                        mCategoryView.setProgressDialogVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(GankCategoryData gankCategoryData) {
                        mCategoryView.setCategoryDate(gankCategoryData.getResults());
                    }
                });
    }
}
