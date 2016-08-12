package cn.bocweb.visainterview.ui;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import cn.bocweb.visainterview.R;
import cn.bocweb.visainterview.base.VIActivity;
import cn.bocweb.visainterview.ui.login.LoginActivity;

public class SplashActivity extends VIActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent in = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                SplashActivity.this.finish();
            }
        };
        new Timer().schedule(task, 3000);
    }

    @Override
    protected boolean showSystemBar() {
        return false;
    }
}
