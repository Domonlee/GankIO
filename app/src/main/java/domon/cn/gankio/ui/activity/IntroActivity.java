package domon.cn.gankio.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.socks.library.KLog;

import domon.cn.gankio.R;
import domon.cn.gankio.data.GankHistoryData;
import domon.cn.gankio.network.RetrofitHttpUtil;
import domon.cn.gankio.ui.fragment.SplashFragment;
import domon.cn.gankio.utils.SharedPreferenceUtil;
import rx.Subscriber;

/**
 * Created by Domon on 16-9-21.
 */

public class IntroActivity extends AppIntro {

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        reqGankHistoryData();

        if (SharedPreferenceUtil.getIntegerValue("isFirstStart") == -1) {
            int color = getResources().getColor(R.color.colorPrimary);
            addSlide(AppIntroFragment.newInstance("最新", "能及时查看干货的最新信息", R.drawable.intro1, color));
            addSlide(AppIntroFragment.newInstance("最全", "所有干货分类信息一网打尽", R.drawable.intro2, color));
            addSlide(AppIntroFragment.newInstance("最美", "读干货学知识看福利长见识", R.drawable.intro3, color));

            setBarColor(color);

            setZoomAnimation();

            setDoneText("立即开启");
            setSkipText("跳过");
        } else {
            //fixme how to close indicator
            addSlide(new SplashFragment());
            showSkipButton(false);
            setProgressButtonEnabled(false);
        }
    }

    @Override
    public void onDonePressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onSkipPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
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
}
