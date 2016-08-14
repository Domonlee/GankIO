package domon.cn.gankio.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.data.GankGirlsData;
import domon.cn.gankio.presenter.IGirlsPresenter;
import domon.cn.gankio.presenter.impl.GirlsPresenterImpl;
import domon.cn.gankio.ui.adapter.BaseRVAdapter;
import domon.cn.gankio.view.IGirlsView;

/**
 * Created by Domon on 16-8-12.
 */
public class GirlsFragment extends Fragment implements IGirlsView {
    @Bind(R.id.girls_rv)
    XRecyclerView mRecyclerView;

    private BaseRVAdapter<GankGirlsData.ResultsEntity> mGankGirlsAdapter;
    private ProgressDialog mProgressDialog;
    private IGirlsPresenter mGirlsPresenter;
    private List<Integer> heights;
    private int mCurrentIndex = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girls, container, false);
        ButterKnife.bind(this, view);

        mProgressDialog = new ProgressDialog(getContext());
        mGirlsPresenter = new GirlsPresenterImpl(this);
        getGankGirlsData();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        if (visibility == View.VISIBLE) {
            mProgressDialog.show();

        } else if (visibility == View.GONE) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void getGankGirlsData() {
        mGirlsPresenter.reqGrilsGankData(mCurrentIndex);
    }

    @Override
    public void setData(final GankGirlsData gankGirlsData) {
        getRandomHeight(gankGirlsData);
        mGankGirlsAdapter = new BaseRVAdapter<GankGirlsData.ResultsEntity>(gankGirlsData.getResults(), getContext()) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_girls;
            }

            @Override
            protected void onBindDataToView(BaseViewHolder holder, GankGirlsData.ResultsEntity resultsEntity, int position) {
                ViewGroup.LayoutParams params = holder.getView(R.id.item_girls_iv).getLayoutParams();
                params.height = heights.get(position);
                holder.getView(R.id.item_girls_iv).setLayoutParams(params);

                holder.setImageFromUrl(R.id.item_girls_iv, resultsEntity.getUrl());
            }
        };

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        mRecyclerView.setAdapter(mGankGirlsAdapter);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                Toast.makeText(getActivity(), "onLoadMore "+ mCurrentIndex, Toast.LENGTH_SHORT).show();
                mCurrentIndex ++;
                // FIXME: 16-8-14 请求数据有问题，替换第二页
                mGirlsPresenter.reqGrilsGankData(mCurrentIndex);
//                getGankGirlsData();
            }
        });
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
    }

    private void getRandomHeight(GankGirlsData gankGirlsData) {
        heights = new ArrayList<>();
        for (int i = 0; i < gankGirlsData.getResults().size(); i++) {
            heights.add((int) (200 + Math.random() * 400));
        }
    }
}
