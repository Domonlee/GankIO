package domon.cn.gankio.presenter.impl;

import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.List;

import domon.cn.gankio.data.GankInfoData;
import domon.cn.gankio.network.Apis;
import domon.cn.gankio.network.BaseCallback;
import domon.cn.gankio.network.OkHttpHelper;
import domon.cn.gankio.presenter.ICategoryPresenter;
import domon.cn.gankio.view.ICategoryView;

/**
 * Created by Domon on 16-8-22.
 */
public class CategoryPresenterImpl implements ICategoryPresenter {
    private ICategoryView mCategoryView;
    private String mUrl;

    public CategoryPresenterImpl(ICategoryView mCategoryView) {
        this.mCategoryView = mCategoryView;
    }


    @Override
    public void reqCategoryData(int type, int index) {

        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();

        mUrl = Apis.GankAllData + Apis.GankCategory[type] + "/15/" + index;

        okHttpHelper.get(mUrl, new BaseCallback<String>() {
            @Override
            public void onRequestBefore() {
                mCategoryView.setProgressDialogVisibility(View.VISIBLE);

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
                    Gson gson = new Gson();
                    //使用泛型
                    List<GankInfoData> list = gson.fromJson(jsonObject.get("results"),
                            new TypeToken<List<GankInfoData>>() {
                            }.getType());
                    mCategoryView.setCategoryDate(list);
                }
                mCategoryView.setProgressDialogVisibility(View.GONE);
            }
        });

    }
}
