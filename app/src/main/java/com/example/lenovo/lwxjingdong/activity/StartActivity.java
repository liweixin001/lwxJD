package com.example.lenovo.lwxjingdong.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovo.lwxjingdong.MainActivity;
import com.example.lenovo.lwxjingdong.R;
//开启初始页面
public class StartActivity extends AppCompatActivity {
    private int time=5;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_start );
        initViews();
    }

    private void initViews() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                while(time>0){
                    time--;
                    try {
                        Thread.sleep(1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(time);
                }
            }
        }.start();
    }
}
