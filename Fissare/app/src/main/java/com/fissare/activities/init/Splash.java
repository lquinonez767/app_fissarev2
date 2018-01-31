package com.fissare.activities.init;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import com.fissare.R;

public class Splash extends AppCompatActivity {

    AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);

        avi = (AVLoadingIndicatorView) findViewById(R.id.indicator);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    avi.show();
                    sleep(2500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent;
                    intent = new Intent(Splash.this, InitAs.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }
}
