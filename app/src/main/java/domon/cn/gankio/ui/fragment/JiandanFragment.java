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

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.presenter.IJiandanPresenter;
import domon.cn.gankio.presenter.impl.JiandanPresenterImpl;
import domon.cn.gankio.ui.adapter.JiandanGirlsDataAdapter;
import domon.cn.gankio.view.IJiandanView;

/**
 * Created by Domon on 16-8-12.
 */
public class JiandanFragment extends Fragment implements IJiandanView {
    @Bind(R.id.girls_rv)
    XRecyclerView mRecyclerView;

    private JiandanGirlsDataAdapter mJiandanGirlsDataAdapter;
    private ProgressDialog mProgressDialog;
    private IJiandanPresenter mJiandanPresenter;
    private int mCurrentIndex = 1;
    private int mLastIndex = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girls, container, false);
        ButterKnife.bind(this, view);

        getJiandanGirlsData();

        mJiandanGirlsDataAdapter = new JiandanGirlsDataAdapter(getContext());
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mJiandanGirlsDataAdapter);

        loadMore();

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
    public void getJiandanGirlsData() {
        mProgressDialog = new ProgressDialog(getContext());
        mJiandanPresenter = new JiandanPresenterImpl(this);
        mJiandanPresenter.reqJiandanGirls(mCurrentIndex);
    }

    @Override
    public void setData(List<String> jiandanGirlsData) {
        if (mCurrentIndex > mLastIndex) {
            mJiandanGirlsDataAdapter.addAllWithNotifyItem(jiandanGirlsData, 10 * mCurrentIndex);
            mLastIndex = mCurrentIndex;
        } else {
            mJiandanGirlsDataAdapter.addAll(jiandanGirlsData);
            mJiandanGirlsDataAdapter.notifyDataSetChanged();
        }
    }

    private void loadMore() {
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++mCurrentIndex;
                getJiandanGirlsData();
                mRecyclerView.loadMoreComplete();
            }
        });
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
    }
}
