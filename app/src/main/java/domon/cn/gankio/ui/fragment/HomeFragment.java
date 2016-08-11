package domon.cn.gankio.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.presenter.IHomePresenter;
import domon.cn.gankio.presenter.impl.HomePresenterImpl;
import domon.cn.gankio.view.IHomeView;

/**
 * Created by Domon on 16-8-10.
 */
public class HomeFragment extends Fragment implements IHomeView {
    @Bind(R.id.home_rv)
    RecyclerView mRecyclerView;

    private IHomePresenter iHomePresenter;
    private ProgressDialog mPorgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        iHomePresenter = new HomePresenterImpl(this);

        mPorgressDialog = new ProgressDialog(getContext());
        getToadyGank();

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
        // TODO: 16-8-11 add dat


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
