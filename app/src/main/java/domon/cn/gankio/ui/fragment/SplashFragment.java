package domon.cn.gankio.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.ui.activity.MainActivity;
import domon.cn.gankio.utils.SharedPreferenceUtil;

/**
 * Created by Domon on 16-9-21.
 */

public class SplashFragment extends Fragment {
    @BindView(R.id.loading_iv)
    ImageView mLoadingIv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_splash, container, false);

        ButterKnife.bind(this, view);

        initImage();

        return view;
    }

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
        Intent intent = new Intent(getActivity(), MainActivity.class);

        startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        SharedPreferenceUtil.setIntegerValue("isFirst", 1);
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
