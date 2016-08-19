package domon.cn.gankio.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import domon.cn.gankio.R;

/**
 * Created by Domon on 16-8-19.
 */
public class ImageViewActivity extends AppCompatActivity {
    @Bind(R.id.myIv)
    ImageView imageView;

    @OnClick(R.id.myIv)
    void onImageViewClice() {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        ButterKnife.bind(this);

        Intent i = getIntent();
        String s = i.getStringExtra("url");
        KLog.e(s);

        Glide.with(this).load(s).into(imageView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
