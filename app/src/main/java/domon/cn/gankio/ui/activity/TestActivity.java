package domon.cn.gankio.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.socks.library.KLog;

import domon.cn.gankio.R;
import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.network.rxAPIs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Domon on 16-9-9.
 */
public class TestActivity extends AppCompatActivity {

    public static String GankBaseUrl = "http://www.gank.io/api/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        rxAPIs rxAPIs = retrofit.create(rxAPIs.class);

        Call<GankContentData> model = rxAPIs.getRxGankInfoData("2016/09/09");

        model.enqueue(new Callback<GankContentData>() {
            @Override
            public void onResponse(Call<GankContentData> call, Response<GankContentData> response) {
                KLog.e();
                KLog.json(String.valueOf(response.body()));
                KLog.e(response.body().getResults().getAndroid().get(0).getDesc());
            }

            @Override
            public void onFailure(Call<GankContentData> call, Throwable t) {
                KLog.e();
            }
        });


    }
}
