package com.willian.yunmusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.willian.yunmusic.BaseActivity;
import com.willian.yunmusic.R;

/**
 * 启动界面
 */
public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
