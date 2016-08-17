package cn.bocweb.visainterview.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.bocweb.visainterview.R;

/**
 * 我的界面
 * Created by fcp on 2016/8/16.
 */
public class MineFragment extends MainFragment {

    @Override
    protected int setContentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void changeToolbar(Activity mActivity, TextView leftView, TextView titleView, TextView rightView,ImageView imageView) {
        leftView.setVisibility(View.INVISIBLE);
        titleView.setText("我的");
        rightView.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
    }
}
