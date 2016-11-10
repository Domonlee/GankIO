package domon.cn.gankio.presenter;

import android.view.View;

import com.socks.library.KLog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domon.cn.gankio.contract.JianDanContract;
import domon.cn.gankio.data.JiandanGirlsData;
import domon.cn.gankio.network.RetrofitHttpUtil;
import domon.cn.gankio.network.rxAPIs;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
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
    //todo test
    private Document mDocument;
    private List<String> titleData;
    private List<String> hrefData;
    private List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> data;
    private Map<String, Object> map;
    //todo test

    public JiandanPresenter(JianDanContract.View mIJiandanView) {
        this.mIJiandanView = mIJiandanView;
        mIJiandanView.setPresenter(this);
    }

    @Override
    public void reqJiandanGirls(String index, String count) {
        mIJiandanView.setProgressBarVisibility(View.VISIBLE);

        getString();

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

    //todo test
    public void getString() {
        String url = "http://www.juzimi.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();

        rxAPIs rxAPIs = retrofit.create(domon.cn.gankio.network.rxAPIs.class);
        Call<ResponseBody> call = rxAPIs.test();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                String doc = null;
                try {
                    doc = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (doc.equals("null")) {
                    KLog.e("null");
                }
                mDocument = Jsoup.parse(doc);
                titleData = new ArrayList<String>();
                Elements es = mDocument.getElementsByClass("xlistju");
                KLog.e("ex size = " + es.size());
                for (Element e : es) {
                    titleData.add(e.text());
                }

                hrefData = new ArrayList<String>();
                Elements es1 = mDocument.getElementsByClass("chromeimg");
                for (Element e : es1) {
                    hrefData.add(e.attr("src"));
                }

                data = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < hrefData.size(); i++) {
                    map = new HashMap<String, Object>();
                    map.put("title", titleData.get(i));
                    map.put("imgUrl", hrefData.get(i));
                    data.add(map);
                    KLog.e(data.get(i).toString());
                }
                mData.addAll(data);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                KLog.e("e");
            }
        });
    }

    @Override
    public void start() {
    }
}
