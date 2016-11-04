package domon.cn.gankio.presenter;

import android.view.View;

import com.socks.library.KLog;

import domon.cn.gankio.contract.JianDanContract;
import domon.cn.gankio.data.JiandanGirlsData;
import domon.cn.gankio.network.RetrofitHttpUtil;
import domon.cn.gankio.network.rxAPIs;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Domon on 16-8-30.
 */
public class JiandanPresenter implements JianDanContract.Presenter {

    private JianDanContract.View mIJiandanView;

    public JiandanPresenter(JianDanContract.View mIJiandanView) {
        this.mIJiandanView = mIJiandanView;
        mIJiandanView.setPresenter(this);
    }

    @Override
    public void reqJiandanGirls(String index, String count) {
        mIJiandanView.setProgressBarVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitHttpUtil.JianDanBaseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(rxAPIs.class)
                .getRxJianDanGirlsDate(index, count)
                .filter(new Func1<JiandanGirlsData, Boolean>() {
                    @Override
                    public Boolean call(JiandanGirlsData jiandanGirlsData) {
                        if (jiandanGirlsData.getCode() == 0) {
                            return true;
                        }
                        return false;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JiandanGirlsData>() {
                    @Override
                    public void onCompleted() {
                        mIJiandanView.setProgressBarVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e);
                        mIJiandanView.setProgressBarVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(JiandanGirlsData jiandanGirlsData) {
                        mIJiandanView.setData(jiandanGirlsData.getResults());
                    }
                });
    }

    @Override
    public void start() {
    }
}
