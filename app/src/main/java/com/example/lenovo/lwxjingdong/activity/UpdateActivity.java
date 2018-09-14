package com.example.lenovo.lwxjingdong.activity;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.lenovo.lwxjingdong.R;


public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_update );
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            Window window=getWindow();

            window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );

            ActionBar actionBar=getSupportActionBar();
            actionBar.hide();
        }
    }
}
