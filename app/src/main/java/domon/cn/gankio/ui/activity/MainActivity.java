package domon.cn.gankio.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.ui.fragment.CategoryFragment;
import domon.cn.gankio.ui.fragment.GirlsFragment;
import domon.cn.gankio.ui.fragment.HomeFragment;
import domon.cn.gankio.ui.fragment.JiandanFragment;
import domon.cn.gankio.utils.FragmentUtils;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private Drawer mDrawer;

    private Context mContext;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        initFragments(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setUpDrawer();
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    private void initFragments(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        mCurrentFragment = (Fragment) mFragmentManager.findFragmentById(R.id.frame_content);
        if (mCurrentFragment == null) {
            mCurrentFragment = FragmentUtils.createFragment(HomeFragment.class);
            mFragmentManager.beginTransaction().add(R.id.frame_content, mCurrentFragment).commit();
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (savedInstanceState != null) {
            List<Fragment> fragments = mFragmentManager.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                transaction.hide(fragments.get(i));
            }
        }
        transaction.show(mCurrentFragment).commitAllowingStateLoss();
    }

    private void setUpDrawer() {
        ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem().withName("GankIO");

        PrimaryDrawerItem itemHome = new PrimaryDrawerItem()
                .withIcon(R.mipmap.drawer_home_icon)
                .withName(R.string.main_main);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem()
                .withIcon(R.mipmap.drawer_category_icon)
                .withName(R.string.main_categroy);
        PrimaryDrawerItem itemGirls = new PrimaryDrawerItem()
                .withIcon(R.mipmap.drawer_girls_icon)
                .withName(R.string.main_grils);
        PrimaryDrawerItem itemJiandan = new PrimaryDrawerItem()
                .withIcon(R.mipmap.drawer_chiken_icon)
                .withName(R.string.main_jiandan);
        PrimaryDrawerItem itemAbout = new PrimaryDrawerItem()
                .withIcon(R.mipmap.drawer_about_icon)
                .withName(R.string.main_about);

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.colorAccent)
                .addProfiles(profileDrawerItem)
                .withOnAccountHeaderItemLongClickListener(new AccountHeader.OnAccountHeaderItemLongClickListener() {
                    @Override
                    public boolean onProfileLongClick(View view, IProfile profile, boolean current) {
                        return true;
                    }
                }).build();

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withAccountHeader(accountHeader)
                .addDrawerItems(
                        itemHome,
                        item2,
                        itemGirls,
                        itemJiandan,
                        new DividerDrawerItem(),
                        itemAbout
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switchDrawer(position);
                        return true;
                    }
                }).build();
    }

    private void switchDrawer(int positon) {

        switchFragment(getClazzandSetTitle(positon));
        mDrawer.closeDrawer();
    }

    private void switchFragment(Class<?> clazz) {
        if (clazz == null) {
            return;
        }

        Fragment to = FragmentUtils.createFragment(clazz);
        if (to.isAdded()) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(to).commitAllowingStateLoss();
        } else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.frame_content, to)
                    .commitAllowingStateLoss();
        }

        mCurrentFragment = to;
    }

    private Class<?> getClazzandSetTitle(int positon) {
        Class<?> clazz = null;

        switch (positon) {
            case 1:
                mToolbar.setTitle(getResources().getString(R.string.main_main));
                clazz = HomeFragment.class;
                break;
            case 2:
                clazz = CategoryFragment.class;
                mToolbar.setTitle(getResources().getString(R.string.main_categroy));
                break;
            case 3:
                mToolbar.setTitle(getResources().getString(R.string.main_grils));
                clazz = GirlsFragment.class;
                break;
            case 4:
                mToolbar.setTitle(R.string.main_jiandan);
                clazz = JiandanFragment.class;
                break;
            default:
                break;
        }
        return clazz;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
