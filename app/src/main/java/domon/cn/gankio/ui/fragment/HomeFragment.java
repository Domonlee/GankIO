package domon.cn.gankio.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.data.GankDateData;
import domon.cn.gankio.presenter.IHomePresenter;
import domon.cn.gankio.presenter.impl.HomePresenterImpl;
import domon.cn.gankio.view.IHomeView;

/**
 * Created by Domon on 16-8-10.
 */
public class HomeFragment extends Fragment implements IHomeView {
    @Bind(R.id.home_tv)
    TextView mHomeTv;

    private GankDateData mGankDateData;
    private GankContentData mGankContentData;
    private IHomePresenter iHomePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
//        setUpData();
//        getContentData();

        iHomePresenter = new HomePresenterImpl(this);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    //    private void setUpData() {
//
//        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
//        okHttpHelper.get(Apis.GanHuoDates, new BaseCallback<GankDateData>() {
//            @Override
//            public void onRequestBefore() {
//
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
//            public void onSuccess(Response response, GankDateData gankDateData) {
//                for (int i = 0; i < gankDateData.getResults().size(); i++) {
//                    gankDateData.getResults().get(i).replace("-", "/");
//                }
//                mGankDateData = gankDateData;
//                KLog.w();
//            }
//        });
//    }
//
//    private void getContentData() {
//        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
//
//        okHttpHelper.get(Apis.GanHuoDataByDay + "2016/08/10", new BaseCallback<GankContentData>() {
//            @Override
//            public void onRequestBefore() {
//                KLog.w();
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
//            public void onSuccess(Response response, GankContentData gankContentData) {
//                mGankContentData = gankContentData;
//
//                mHomeTv.setText(mGankContentData.getCategory().toString());
//            }
//        });
//    }

    @Override
    public void getToadyGank() {
        // TODO: 16-8-11 data is null
        GankContentData gankContentData = new GankContentData();
        gankContentData = iHomePresenter.reqHomeGankData();
        KLog.e(gankContentData.getCategory().toString());
    }
}
