package domon.cn.gankio;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;

import domon.cn.gankio.utils.SharedPreferenceUtil;

/**
 * Created by Domon on 16-8-19.
 */
public class MyApp extends Application {
    private static MyApp mInstance = null;

    private static Context mContext;

    public static MyApp getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();

        SharedPreferenceUtil.initPreference(mContext);

        AVOSCloud.initialize(this,"OpA9Ony5boPXykGEPUUK2h3T-gzGzoHsz","GaCbQI6lhPifuvOGF2MmmTjx");
        AVOSCloud.useAVCloudCN();
    }
}
