package domon.cn.gankio.ui.fragment;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.contract.GirlsContract;
import domon.cn.gankio.data.GankGirlsData;
import domon.cn.gankio.presenter.impl.GirlsPresenterImpl;
import domon.cn.gankio.ui.adapter.GankGirlsDataAdapter;

/**
 * Created by Domon on 16-8-12.
 */
public class GirlsFragment extends Fragment implements GirlsContract.View {
    @Bind(R.id.girls_rv)
    XRecyclerView mRecyclerView;

    private GankGirlsDataAdapter mGankGirlsAdapter;
    private GirlsContract.Presenter mGirlsPresenter;
    private int mCurrentIndex = 1;
    private int mLastIndex = 1;
    private int mCount = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girls, container, false);
        ButterKnife.bind(this, view);

        getGankGirlsData();

        mGankGirlsAdapter = new GankGirlsDataAdapter(getContext());
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mGankGirlsAdapter);

        loadMore();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void getGankGirlsData() {
        mGirlsPresenter = new GirlsPresenterImpl(this,getContext());
        mGirlsPresenter.reqGrilsGankData(mCurrentIndex + "", mCount + "");
    }

    @Override
    public void setData(final GankGirlsData gankGirlsData) {
        if (mCurrentIndex > mLastIndex) {
            mGankGirlsAdapter.addAllWithNotifyItem(gankGirlsData.getResults(), mCount * mCurrentIndex);
            mLastIndex = mCurrentIndex;
        } else {
            mGankGirlsAdapter.addAll(gankGirlsData.getResults());
            mGankGirlsAdapter.notifyDataSetChanged();
        }
    }

    private void loadMore() {
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mCurrentIndex = 1;
                mGirlsPresenter.reqGrilsGankData(mCurrentIndex + "", "10");
                mRecyclerView.refreshComplete();
                Toast.makeText(getContext(), "refresh successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadMore() {
                ++mCurrentIndex;
                getGankGirlsData();
                mRecyclerView.loadMoreComplete();
            }
        });
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
    }

    @Override
    public void setPresenter(GirlsContract.Presenter presenter) {


    }
}
