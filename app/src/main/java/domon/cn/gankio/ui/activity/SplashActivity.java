package domon.cn.gankio.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.data.GankHistoryData;
import domon.cn.gankio.network.RetrofitHttpUtil;
import domon.cn.gankio.utils.SharedPreferenceUtil;
import rx.Subscriber;

/**
 * Created by Domon on 16-9-20.
 */

public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.loading_iv)
    ImageView mLoadingIv;

    private Context mContext;
    private boolean isUpdate = false;
    private String mUpdateUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        mContext = this;
        ButterKnife.bind(this);

        reqGankHistoryData();

        initImage();
    }

    private void reqGankHistoryData() {
        Subscriber<GankHistoryData> subscriber = new Subscriber<GankHistoryData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                KLog.e(e);
            }

            @Override
            public void onNext(GankHistoryData gankHistoryData) {
                SharedPreferenceUtil.setStrListValue("gankDateInfoList", gankHistoryData.getResults());
                KLog.e();
            }
        };
        RetrofitHttpUtil.getInstance().getRxGankHistoryDare(subscriber);
    }

//    private void reqAppUpdate(){
//        AVQuery<AVObject> avQuery = new AVQuery<>("app_update_info");
//        avQuery.getInBackground("57e10631128fe10064de44a1", new GetCallback<AVObject>() {
//            @Override
//            public void done(AVObject avObject, AVException e) {
//                int cVersionCode = PackageUtil.getAppVersionCode(mContext);
//                int sVersionCode = avObject.getInt("versionCode");
//
//                if (sVersionCode>cVersionCode){
//                    isUpdate = true;
//                    mUpdateUrl = avObject.getString("downloadUrl");
//                }else {
//                    isUpdate = false;
//                }
//            }
//        });
//    }

    private void initImage() {

        mLoadingIv.setImageResource(R.mipmap.splash_icon);

        final ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                stratActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mLoadingIv.startAnimation(scaleAnimation);
    }

    private void stratActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//        intent.putExtra("isUpdate",isUpdate);
//        if (isUpdate){
//            intent.putExtra("updateUrl",mUpdateUrl);
//        }

        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
