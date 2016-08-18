package domon.cn.gankio.presenter.impl;

import android.view.View;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.network.Apis;
import domon.cn.gankio.network.BaseCallback;
import domon.cn.gankio.network.OkHttpHelper;
import domon.cn.gankio.presenter.IHomePresenter;
import domon.cn.gankio.view.IHomeView;

/**
 * Created by Domon on 16-8-11.
 */
public class HomePresenterImpl implements IHomePresenter {
    private IHomeView iHomeView;

    public HomePresenterImpl(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
    }

    @Override
    public void reqHomeGankData() {
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();

        okHttpHelper.get(Apis.GanHuoDataByDay + "2016/08/18", new BaseCallback<GankContentData>() {
                    @Override
                    public void onRequestBefore() {
                        iHomeView.setProgressDialogVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Request request, Exception e) {

                    }

                    @Override
                    public void onError(Response response, int errorCode, Exception e) {

                    }

                    @Override
                    public void onSuccess(Response response, GankContentData gankContentData) {
                        if (!gankContentData.isError()) {
                            iHomeView.setProgressDialogVisibility(View.GONE);
                            iHomeView.setData(gankContentData);

                        }
                    }
                }
        );

    }
}
