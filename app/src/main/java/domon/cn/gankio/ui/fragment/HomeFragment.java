package domon.cn.gankio.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.data.GankDateData;
import domon.cn.gankio.presenter.IHomePresenter;
import domon.cn.gankio.presenter.impl.HomePresenterImpl;
import domon.cn.gankio.ui.adapter.GankContentAdapter;
import domon.cn.gankio.view.IHomeView;

/**
 * Created by Domon on 16-8-10.
 */
public class HomeFragment extends Fragment implements IHomeView {
    @Bind(R.id.home_rv)
    RecyclerView mRecyclerView;

    private IHomePresenter iHomePresenter;
    private ProgressDialog mPorgressDialog;
    private GankContentAdapter mGankContentAdapter;

    private List<GankDateData> mGankDateDatas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);


        mGankDateDatas = new ArrayList<>();

        mPorgressDialog = new ProgressDialog(getContext());
        iHomePresenter = new HomePresenterImpl(this);
        getToadyGank();

        mGankContentAdapter = new GankContentAdapter(getContext());
        mRecyclerView.setAdapter(mGankContentAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void getToadyGank() {
        iHomePresenter.reqHomeGankData();
    }

    @Override
    public void setData(GankContentData data) {
        if (data != null) {
            if (data.getResults().getAndroid() != null) {
                mGankDateDatas.addAll(data.getResults().getAndroid());
            }
            if (data.getResults().getiOS() != null) {
                mGankDateDatas.addAll(data.getResults().getiOS());
            }
            if (data.getResults().get休息视频() != null) {
                mGankDateDatas.addAll(data.getResults().get休息视频());
            }
            if (data.getResults().get拓展资源() != null) {
                mGankDateDatas.addAll(data.getResults().get拓展资源());
            }
            if (data.getResults().get瞎推荐() != null) {
                mGankDateDatas.addAll(data.getResults().get瞎推荐());
            }
            if (data.getResults().get福利() != null) {
                mGankDateDatas.addAll(data.getResults().get福利());
            }
        }

        mGankContentAdapter.addAll(mGankDateDatas);
        KLog.e();
    }

    @Override
    public void setProgressDialogVisibility(int visibility) {
        if (visibility == View.GONE) {
            mPorgressDialog.dismiss();
        } else if (visibility == View.VISIBLE) {
            mPorgressDialog.show();
        }
    }
}
