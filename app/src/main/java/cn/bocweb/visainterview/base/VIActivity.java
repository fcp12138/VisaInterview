package cn.bocweb.visainterview.base;

import android.os.Bundle;

import com.fcp.baselibrary.ui.activity.ToolbarActivity;

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
}
