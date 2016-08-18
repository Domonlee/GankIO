package domon.cn.gankio.presenter.impl;

import android.view.View;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import domon.cn.gankio.data.GankGirlsData;
import domon.cn.gankio.network.Apis;
import domon.cn.gankio.network.BaseCallback;
import domon.cn.gankio.network.OkHttpHelper;
import domon.cn.gankio.presenter.IGirlsPresenter;
import domon.cn.gankio.view.IGirlsView;

/**
 * Created by Domon on 16-8-12.
 */
// TODO: 16-8-12 添加data
public class GirlsPresenterImpl implements IGirlsPresenter {
    private IGirlsView mIGirlsView;
    private String reqUrl;

    public GirlsPresenterImpl(IGirlsView iGirlsView) {
        this.mIGirlsView = iGirlsView;
    }

    @Override
    public void reqGrilsGankData(final int index) {

        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        reqUrl = Apis.GanHuoData + "福利/4/" + index;

        okHttpHelper.get(reqUrl, new BaseCallback<GankGirlsData>() {
            @Override
            public void onRequestBefore() {
                setProgressBarVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }

            @Override
            public void onSuccess(Response response, GankGirlsData gankGirlsData) {
                setProgressBarVisibility(View.GONE);
                mIGirlsView.setData(gankGirlsData);
            }
        });
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        mIGirlsView.setProgressBarVisibility(visibility);
    }
}
