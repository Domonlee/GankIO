package domon.cn.gankio.presenter.impl;

import android.view.View;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.socks.library.KLog;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;

import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.network.Apis;
import domon.cn.gankio.network.BaseCallback;
import domon.cn.gankio.network.OkHttpHelper;
import domon.cn.gankio.presenter.IHomePresenter;
import domon.cn.gankio.utils.SharedPreferenceUtil;
import domon.cn.gankio.view.IHomeView;

/**
 * Created by Domon on 16-8-11.
 */
public class HomePresenterImpl implements IHomePresenter {
    private IHomeView iHomeView;

    public HomePresenterImpl(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
    }

    private String getUrl() {
        List<String> dateInfoList = SharedPreferenceUtil.getStrListValue("gankDateInfoList");
        return Apis.GankDataByDay + dateInfoList.get(0);
    }

    @Override
    public void reqHomeGankData() {


        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();

        okHttpHelper.get(getUrl(), new BaseCallback<GankContentData>() {
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

    @Override
    public void reqDateInfo() {

        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();

        okHttpHelper.get(Apis.GankHistoryDates, new BaseCallback<String>() {
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
            public void onSuccess(Response response, String string) {
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(string).getAsJsonObject();

                if (jsonObject.get("error").getAsString().equals("false")) {
                    KLog.e("in");
                    List<String> dateList = new ArrayList<String>();
                    JsonArray list = jsonObject.getAsJsonArray("results");
                    for (int i = 0; i < list.size(); i++) {
                        dateList.add(i, list.get(i).getAsString().replace("-", "/"));
                    }
                    iHomeView.setProgressDialogVisibility(View.GONE);
                    iHomeView.setDateInfo(dateList);
                }
            }
        });
    }
}
