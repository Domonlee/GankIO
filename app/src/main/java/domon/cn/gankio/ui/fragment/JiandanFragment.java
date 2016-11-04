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
import domon.cn.gankio.contract.JianDanContract;
import domon.cn.gankio.presenter.JiandanPresenter;
import domon.cn.gankio.ui.adapter.JiandanGirlsDataAdapter;

/**
 * Created by Domon on 16-8-12.
 */
public class JiandanFragment extends Fragment implements JianDanContract.View {
    @Bind(R.id.girls_rv)
    XRecyclerView mRecyclerView;

    private JiandanGirlsDataAdapter mJiandanGirlsDataAdapter;
    private ProgressDialog mProgressDialog;
    private JianDanContract.Presenter mJiandanPresenter;
    private int mCurrentIndex = 1;
    private int mLastIndex = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJiandanGirlsDataAdapter = new JiandanGirlsDataAdapter(getContext());
        mProgressDialog = new ProgressDialog(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girls, container, false);
        ButterKnife.bind(this, view);

        mJiandanPresenter = new JiandanPresenter(this);
        mJiandanPresenter.reqJiandanGirls(mCurrentIndex + "", "10");

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
                mJiandanPresenter.reqJiandanGirls(++mCurrentIndex + "", "10");
                mRecyclerView.loadMoreComplete();
            }
        });
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
    }

    @Override
    public void setPresenter(JianDanContract.Presenter presenter) {
        if (presenter != null) {
            mJiandanPresenter = presenter;
            return;
        }
    }
}
