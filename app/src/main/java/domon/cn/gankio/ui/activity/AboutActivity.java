package domon.cn.gankio.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import domon.cn.gankio.R;

/**
 * Created by Domon on 17-1-12.
 */

public class AboutActivity extends AppCompatActivity{
    @OnClick(R.id.about_back_iv)
    void OnBackClick(){
        finish();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context,AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);
    }
}
