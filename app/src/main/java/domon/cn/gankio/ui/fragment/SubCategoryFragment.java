package domon.cn.gankio.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.network.Apis;

/**
 * Created by Domon on 16-8-21.
 */
public class SubCategoryFragment extends Fragment {
    public static final int TYPE_ALL = 0;
    public static final int TYPE_FULI = 1;
    public static final int TYPE_ANDROID = 2;
    public static final int TYPE_IOS = 3;
    public static final int TYPE_拓展资源 = 4;
    public static final int TYPE_前端 = 5;
    public static final int TYPE_瞎推荐 = 6;
    public static final int TYPE_休息视频 = 7;

    @Bind(R.id.category_rv)
    RecyclerView mRecyclerView;

    @Bind(R.id.mytv)
    TextView mTextView;

    private int mType = 1;

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
        View view = inflater.inflate(R.layout.fragment_subcategory, container,false);
        ButterKnife.bind(this, view);

        mTextView.setText(Apis.GankCategory[mType]);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
