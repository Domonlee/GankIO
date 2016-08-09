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
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    }

    private void setUpDrawer() {

        ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem().withName("点击登录");

        PrimaryDrawerItem itemHome = new PrimaryDrawerItem()
                .withIcon(R.mipmap.ic_launcher)
                .withName("Home");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem()
                .withIcon(R.mipmap.ic_launcher)
                .withName("second");

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.ic_launcher)
                .addProfiles(profileDrawerItem)
                .withOnAccountHeaderItemLongClickListener(new AccountHeader.OnAccountHeaderItemLongClickListener() {
                    @Override
                    public boolean onProfileLongClick(View view, IProfile profile, boolean current) {
                        return false;
                    }
                }).build();

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withAccountHeader(accountHeader)
                .addDrawerItems(
                        itemHome,
                        new DividerDrawerItem(),
                        item2,
                        new SecondaryDrawerItem().withName("hello")
                                .withIcon(R.mipmap.ic_launcher)
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
