package com.fcp.baselibrary.ui.activity;

import android.view.View;
import android.widget.FrameLayout;


/**
 * 有等待进度条的Activity
 * Created by fcp on 2016/3/5.
 */
public abstract class ProgressActivity extends ToolbarActivity {

    private View content;


    @Override
    protected View initUserLayout(int layoutResID) {
        content = layoutInflater.inflate(layoutResID, null);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.addView(content , FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        //进度圈
        //TODO
        return frameLayout;
    }

    protected void showContent(){
    }

    protected void showProgress(){
    }


}
