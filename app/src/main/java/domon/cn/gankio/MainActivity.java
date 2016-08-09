package domon.cn.gankio;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.socks.library.KLog;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.network.BaseCallback;
import domon.cn.gankio.network.OkHttpHelper;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private Drawer mDrawer;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        setUpDrawer();

        getSupportActionBar().setTitle("Hello World");

        initRequest();
    }

    private void initRequest() {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance();
        String url = "http://www.baidu.com";
        httpHelper.get(url, new BaseCallback<String>() {
            @Override
            public void onRequestBefore() {
                KLog.e("Before");
            }

            @Override
            public void onFailure(Request request, Exception e) {
                KLog.e("Failure");
            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {
                KLog.e("Error");
            }

            @Override
            public void onSuccess(Response response, String s) {
                KLog.e("OnSuccess");
                KLog.e(s);
            }
        });

    }

    private void setUpDrawer() {

        ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem().withName("点击登录");

        PrimaryDrawerItem itemHome = new PrimaryDrawerItem()
                .withIcon(R.mipmap.ic_launcher)
                .withName("首页");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem()
                .withIcon(R.mipmap.ic_launcher)
                .withName("分类");
        PrimaryDrawerItem itemAbout = new PrimaryDrawerItem()
                .withIcon(R.mipmap.ic_launcher)
                .withName("关于");

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
                        new DividerDrawerItem(),
                        itemAbout
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_LONG).show();
                        return true;
                    }
                }).build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
