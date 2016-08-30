package domon.cn.gankio.presenter.impl;

import android.view.View;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import domon.cn.gankio.data.JiandanGirlsData;
import domon.cn.gankio.network.BaseCallback;
import domon.cn.gankio.network.OkHttpHelper;
import domon.cn.gankio.presenter.IJiandanPresenter;
import domon.cn.gankio.view.IJiandanView;

/**
 * Created by Domon on 16-8-30.
 */
public class JiandanPresenterImpl implements IJiandanPresenter {

    private IJiandanView mIJiandanView;
    private String reqUrl;

    public JiandanPresenterImpl(IJiandanView mIJiandanView) {
        this.mIJiandanView = mIJiandanView;
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        mIJiandanView.setProgressBarVisibility(visibility);
    }

    @Override
    public void reqJiandanGirls(int index) {
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        reqUrl = "http://pho.orrindeng.com/pho/getpho/" + index + "/10";

        okHttpHelper.get(reqUrl, new BaseCallback<JiandanGirlsData>() {
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
            public void onSuccess(Response response, JiandanGirlsData jiandanGirlsData) {
                setProgressBarVisibility(View.GONE);
                mIJiandanView.setData(jiandanGirlsData.getResults());
            }
        });
    }
}
