package domon.cn.gankio.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import domon.cn.gankio.network.Apis;
import domon.cn.gankio.ui.fragment.SubCategoryFragment;

/**
 * Created by Domon on 16-8-21.
 */
public class CategoryViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();

    public CategoryViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(SubCategoryFragment.newInstance(SubCategoryFragment.TYPE_ALL));
        fragmentList.add(SubCategoryFragment.newInstance(SubCategoryFragment.TYPE_FULI));
        fragmentList.add(SubCategoryFragment.newInstance(SubCategoryFragment.TYPE_ANDROID));
        fragmentList.add(SubCategoryFragment.newInstance(SubCategoryFragment.TYPE_IOS));
        fragmentList.add(SubCategoryFragment.newInstance(SubCategoryFragment.TYPE_拓展资源));
        fragmentList.add(SubCategoryFragment.newInstance(SubCategoryFragment.TYPE_前端));
        fragmentList.add(SubCategoryFragment.newInstance(SubCategoryFragment.TYPE_瞎推荐));
        fragmentList.add(SubCategoryFragment.newInstance(SubCategoryFragment.TYPE_休息视频));
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size() == 0 ? 0 : fragmentList.size();
    }

    /**
     * 切记一定要写这个方法,同tablayout一起联动标题
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return Apis.GankCategory[position];
    }
}
