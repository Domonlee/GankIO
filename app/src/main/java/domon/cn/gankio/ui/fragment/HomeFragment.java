package domon.cn.gankio.ui.fragment;

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
import domon.cn.gankio.contract.HomeContract;
import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.data.GankInfoData;
import domon.cn.gankio.presenter.HomePresenter;
import domon.cn.gankio.ui.adapter.GankContentAdapter;

/**
 * Created by Domon on 16-8-10.
 */
public class HomeFragment extends Fragment implements HomeContract.View {
    @Bind(R.id.home_rv)
    RecyclerView mRecyclerView;

    private HomeContract.Presenter iHomePresenter;
    private GankContentAdapter mGankContentAdapter;

    private List<GankInfoData> mGankInfoDatas = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();
        iHomePresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        initView();
        iHomePresenter = new HomePresenter(this, getActivity());

        return view;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        if (presenter != null) {
            iHomePresenter = presenter;
            KLog.e(presenter);
            return;
        }
        KLog.e("presenter is null");
    }

    private void initView() {
        mGankContentAdapter = new GankContentAdapter(getContext());
        mRecyclerView.setAdapter(mGankContentAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setData(GankContentData data) {
        if (data != null) {
            if (data.getResults().getAndroid() != null) {

                for (int i = 0; i < data.getResults().getAndroid().size(); i++) {
                    if (i != 0) {
                        data.getResults().getAndroid().get(i).setType("sameCategory");
                    }
                }

                mGankInfoDatas.addAll(data.getResults().getAndroid());
            }
            if (data.getResults().getiOS() != null) {
                for (int i = 0; i < data.getResults().getiOS().size(); i++) {
                    if (i != 0) {
                        data.getResults().getiOS().get(i).setType("sameCategory");
                    }
                }
                mGankInfoDatas.addAll(data.getResults().getiOS());
            }
            if (data.getResults().get休息视频() != null) {
                for (int i = 0; i < data.getResults().get休息视频().size(); i++) {
                    if (i != 0) {
                        data.getResults().get休息视频().get(i).setType("sameCategory");
                    }
                }
                mGankInfoDatas.addAll(data.getResults().get休息视频());
            }
            if (data.getResults().get拓展资源() != null) {
                for (int i = 0; i < data.getResults().get拓展资源().size(); i++) {
                    if (i != 0) {
                        data.getResults().get拓展资源().get(i).setType("sameCategory");
                    }
                }
                mGankInfoDatas.addAll(data.getResults().get拓展资源());
            }
            if (data.getResults().get瞎推荐() != null) {
                for (int i = 0; i < data.getResults().get瞎推荐().size(); i++) {
                    if (i != 0) {
                        data.getResults().get瞎推荐().get(i).setType("sameCategory");
                    }
                }
                mGankInfoDatas.addAll(data.getResults().get瞎推荐());
            }
            if (data.getResults().get福利() != null) {
                for (int i = 0; i < data.getResults().get福利().size(); i++) {
                    if (i != 0) {
                        data.getResults().get福利().get(i).setType("sameCategory");
                    }
                }
                mGankInfoDatas.addAll(data.getResults().get福利());
            }
        }

        mGankContentAdapter.addAll(mGankInfoDatas);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGankContentAdapter.clearAll();
        ButterKnife.unbind(this);
    }
}
