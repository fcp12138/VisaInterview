package cn.bocweb.visainterview.base;

import android.content.Intent;
import android.os.Bundle;

import com.fcp.baselibrary.ui.activity.ToolbarActivity;

import cn.bocweb.visainterview.R;
import cn.bocweb.visainterview.manager.ActivityStackManager;

/**
 * 面签项目基类
 * Created by fcp on 2016/8/11.
 */
public abstract class VIActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //入栈
        ActivityStackManager.getInstance().pushOneActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //出栈
        ActivityStackManager.getInstance().popOneActivity(this);
    }

    @Override
    protected int getToolbarColorSource() {
        return R.color.colortip;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    // Press the back button in mobile phone
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
