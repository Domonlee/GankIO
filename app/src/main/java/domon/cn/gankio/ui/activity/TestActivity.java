package domon.cn.gankio.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import domon.cn.gankio.R;

/**
 * Created by Domon on 16-9-9.
 */
public class TestActivity extends AppCompatActivity {

    public static String GankBaseUrl = "http://www.gank.io/api/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}
