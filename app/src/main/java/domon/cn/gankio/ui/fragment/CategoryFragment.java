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
import domon.cn.gankio.network.Apis;
import domon.cn.gankio.ui.adapter.CategoryViewPagerAdapter;

/**
 * Created by Domon on 16-8-19.
 */
public class CategoryFragment extends Fragment {
    @Bind(R.id.category_vp)
    ViewPager mViewPager;

    @Bind(R.id.category_tab_layout)
    TabLayout mTabLayout;

    private CategoryViewPagerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);

        for (int i = 0; i < Apis.GankCategory.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(Apis.GankCategory[i]));
        }

        mAdapter = new CategoryViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);

        //联动tablayout&viewpager
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}
