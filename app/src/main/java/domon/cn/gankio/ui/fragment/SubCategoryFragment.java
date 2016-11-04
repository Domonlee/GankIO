package domon.cn.gankio.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.contract.CategoryContract;
import domon.cn.gankio.data.GankInfoData;
import domon.cn.gankio.presenter.CategoryPresenter;
import domon.cn.gankio.ui.adapter.CategoryDetailAdapter;

/**
 * Created by Domon on 16-8-21.
 */
public class SubCategoryFragment extends Fragment implements CategoryContract.View {
    public static final int TYPE_ALL = 0;
    public static final int TYPE_FULI = 1;
    public static final int TYPE_ANDROID = 2;
    public static final int TYPE_IOS = 3;
    public static final int TYPE_拓展资源 = 4;
    public static final int TYPE_前端 = 5;
    public static final int TYPE_瞎推荐 = 6;
    public static final int TYPE_休息视频 = 7;

    @Bind(R.id.category_rv)
    XRecyclerView mRecyclerView;

    private Context mContext;
    private CategoryContract.Presenter mCategoryPresenter;
    private CategoryDetailAdapter mAdapter;
    private int mType = 1;
    private int mIndex = 1;

    public static SubCategoryFragment newInstance(int type) {
        SubCategoryFragment subCategoryFragment = new SubCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        subCategoryFragment.setArguments(bundle);
        return subCategoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mType = args.getInt("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subcategory, container, false);
        ButterKnife.bind(this, view);

        mContext = getContext();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CategoryDetailAdapter(mContext, mType);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mIndex++;
                mCategoryPresenter.reqCategoryData(mType, mIndex + "", "10");
                mRecyclerView.loadMoreComplete();
            }
        });

        reqCategoryData(mType);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void reqCategoryData(int type) {
        mCategoryPresenter = new CategoryPresenter(this, getContext());
        mCategoryPresenter.reqCategoryData(type, mIndex + "", "10");
    }

    @Override
    public void setCategoryDate(List<GankInfoData> gankInfoDatas) {
        mAdapter.addAll(gankInfoDatas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(CategoryContract.Presenter presenter) {
        if (presenter != null) {
            mCategoryPresenter = presenter;
            return;
        }
    }
}
