package domon.cn.gankio.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.data.HistoryData;
import domon.cn.gankio.network.BaseCallback;
import domon.cn.gankio.network.OkHttpHelper;

/**
 * Created by Domon on 16-8-10.
 */
public class HomeFragment extends Fragment {
    @Bind(R.id.home_tv)
    TextView mHomeTv;
    private Html.ImageGetter mImageGetter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        setUpData();

        mImageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String s) {
                Drawable drawable = null;
                try {
                    URL url = new URL(s);
                    drawable = Drawable.createFromStream(url.openStream(),"");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                return drawable;
            }
        };

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void setUpData() {

        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        String url = "http://gank.io/api/history/content/2/1";
        okHttpHelper.get(url, new BaseCallback<HistoryData>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }

            @Override
            public void onSuccess(Response response, HistoryData historyData) {
                mHomeTv.setText(Html.fromHtml(historyData.getResults().get(0).getContent(),
                        mImageGetter, null));
            }
        });
    }


}
