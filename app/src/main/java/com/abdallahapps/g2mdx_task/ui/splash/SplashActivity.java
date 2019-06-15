package com.abdallahapps.g2mdx_task.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.abdallahapps.g2mdx_task.APP;
import com.abdallahapps.g2mdx_task.R;
import com.abdallahapps.g2mdx_task.ui.base.view.BaseActivity;
import com.abdallahapps.g2mdx_task.ui.login.view.LoginActivity;

import androidx.annotation.Nullable;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        APP.context = this;

        /// i can check if user login before then open home screen ;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        },1000);
    }

}
