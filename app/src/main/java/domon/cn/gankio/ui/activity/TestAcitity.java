package domon.cn.gankio.ui.activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.socks.library.KLog;

import java.io.IOException;

import domon.cn.gankio.R;

/**
 * Created by Domon on 16-9-29.
 */

public class TestAcitity extends AppCompatActivity {
    private Button mBtn;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mBtn = (Button) findViewById(R.id.my_bt);

        mediaPlayer = new MediaPlayer();

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            }
        });

        final String url = "http://mix.zhenai80.com:81/UUPath/201609/1609J019.mp3";

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }

                try {
                    KLog.e();
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
