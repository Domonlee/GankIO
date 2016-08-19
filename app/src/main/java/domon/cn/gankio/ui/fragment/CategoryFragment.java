package domon.cn.gankio.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;

/**
 * Created by Domon on 16-8-19.
 */
public class CategoryFragment extends Fragment {
    @Bind(R.id.category_vp)
    ViewPager mViewPager;

    @Bind(R.id.category_tab_layout)
    TabLayout mTabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);

        mViewPager = new ViewPager(getContext());
        for (int i = 0; i < 5; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(i + ""));
        }

        return view;
    }
}
