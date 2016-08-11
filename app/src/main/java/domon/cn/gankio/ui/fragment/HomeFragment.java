package domon.cn.gankio.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        // TODO: 16-8-11 data is null
        iHomePresenter.reqHomeGankData();
    }

    @Override
    public void setData(GankContentData data) {
        mHomeTv.setText(data.toString());
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
