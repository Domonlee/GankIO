package domon.cn.gankio.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.data.GankGirlsData;
import domon.cn.gankio.presenter.IGirlsPresenter;
import domon.cn.gankio.presenter.impl.GirlsPresenterImpl;
import domon.cn.gankio.ui.adapter.GankGirlsAdapter;
import domon.cn.gankio.view.IGirlsView;

/**
 * Created by Domon on 16-8-12.
 */
public class GirlsFragment extends Fragment implements IGirlsView {
    @Bind(R.id.girls_rv)
    RecyclerView mRecyclerView;

    private GankGirlsAdapter mGankGirlsAdapter;
    private ProgressDialog mProgressDialog;
    private IGirlsPresenter mGirlsPresenter;

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
        mGirlsPresenter.reqGrilsGankData();
    }

    @Override
    public void setData(GankGirlsData gankGirlsData) {
        mGankGirlsAdapter = new GankGirlsAdapter(getContext(), gankGirlsData);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        mRecyclerView.setAdapter(mGankGirlsAdapter);

    }
}
