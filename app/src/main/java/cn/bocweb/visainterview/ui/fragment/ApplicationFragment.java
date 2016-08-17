package cn.bocweb.visainterview.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fcp.baselibrary.ui.fragment.WaitFragment;

import cn.bocweb.visainterview.R;

/**
 * 应用界面
 * Created by fcp on 2016/8/16.
 */
public class ApplicationFragment extends MainFragment {

    private GridView mApplicationGrid;

    @Override
    protected int setContentLayoutId() {
        return R.layout.fragment_application;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mApplicationGrid = (GridView) view.findViewById(R.id.application_grid);
    }

    @Override
    public void changeToolbar(Activity mActivity, TextView leftView, TextView titleView, TextView rightView,ImageView imageView) {
        leftView.setVisibility(View.INVISIBLE);
        titleView.setText("应用");
        rightView.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
    }
}
