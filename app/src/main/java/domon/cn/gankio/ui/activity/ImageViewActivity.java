package domon.cn.gankio.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import domon.cn.gankio.R;

/**
 * Created by Domon on 16-8-19.
 */
public class ImageViewActivity extends AppCompatActivity {
    @BindView(R.id.myIv)
    ImageView imageView;

    @OnClick(R.id.myIv)
    void onImageViewClice() {
        finish();
    }

    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context,ImageViewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
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
    }
}
