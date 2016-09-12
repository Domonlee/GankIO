package domon.cn.gankio.presenter.impl;

import android.view.View;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.network.Apis;
import domon.cn.gankio.network.rxAPIs;
import domon.cn.gankio.presenter.IHomePresenter;
import domon.cn.gankio.ui.activity.TestActivity;
import domon.cn.gankio.utils.SharedPreferenceUtil;
import domon.cn.gankio.view.IHomeView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        //todo change retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestActivity.GankBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        rxAPIs rxAPIs = retrofit.create(domon.cn.gankio.network.rxAPIs.class);

        //// FIXME: 16-9-12 maybe null
        Call<GankContentData> model = rxAPIs.getRxGankInfoData(SharedPreferenceUtil.getStrListValue("gankDateInfoList").get(0));

        model.enqueue(new Callback<GankContentData>() {
            @Override
            public void onResponse(Call<GankContentData> call, retrofit2.Response<GankContentData> response) {
                KLog.e();
                iHomeView.setProgressDialogVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    iHomeView.setProgressDialogVisibility(View.GONE);
                    iHomeView.setData(response.body());
                }
            }

            @Override
            public void onFailure(Call<GankContentData> call, Throwable t) {
                KLog.e();
                iHomeView.setProgressDialogVisibility(View.GONE);

            }
        });
    }

    @Override
    public void reqDateInfo() {

        Retrofit retorfit = new Retrofit.Builder()
                .baseUrl(TestActivity.GankBaseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
                .build();

        rxAPIs rxAPIs = retorfit.create(domon.cn.gankio.network.rxAPIs.class);

        Call<String> model = rxAPIs.getRxGankHistoryDate();

        model.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                KLog.e();
                if (response.isSuccessful()) {
                    JsonParser jsonParser = new JsonParser();
                    JsonObject jsonObject = jsonParser.parse(response.body()).getAsJsonObject();

                    if (jsonObject.get("error").getAsString().equals("false")) {
                        List<String> dateList = new ArrayList<String>();
                        JsonArray list = jsonObject.getAsJsonArray("results");
                        for (int i = 0; i < list.size(); i++) {
                            dateList.add(i, list.get(i).getAsString().replace("-", "/"));
                        }
                        iHomeView.setProgressDialogVisibility(View.GONE);
                        iHomeView.setDateInfo(dateList);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                KLog.e(t);

            }
        });

//        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
//
//        okHttpHelper.get(Apis.GankHistoryDates, new BaseCallback<String>() {
//            @Override
//            public void onRequestBefore() {
//                iHomeView.setProgressDialogVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onFailure(Request request, Exception e) {
//
//            }
//
//            @Override
//            public void onError(Response response, int errorCode, Exception e) {
//
//            }
//
//            @Override
//            public void onSuccess(Response response, String string) {
//                JsonParser jsonParser = new JsonParser();
//                JsonObject jsonObject = jsonParser.parse(string).getAsJsonObject();
//
//                if (jsonObject.get("error").getAsString().equals("false")) {
//                    List<String> dateList = new ArrayList<String>();
//                    JsonArray list = jsonObject.getAsJsonArray("results");
//                    for (int i = 0; i < list.size(); i++) {
//                        dateList.add(i, list.get(i).getAsString().replace("-", "/"));
//                    }
//                    iHomeView.setProgressDialogVisibility(View.GONE);
//                    iHomeView.setDateInfo(dateList);
//                }
//            }
//        });
    }
}
