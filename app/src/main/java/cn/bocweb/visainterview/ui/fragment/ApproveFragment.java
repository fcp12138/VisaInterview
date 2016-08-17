package cn.bocweb.visainterview.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fcp.baselibrary.ui.fragment.BaseFragment;

import cn.bocweb.visainterview.R;

/**
 * 审批界面
 * Created by fcp on 2016/8/16.
 */
public class ApproveFragment extends MainFragment {

    @Override
    protected int setContentLayoutId() {
        return R.layout.fragment_approve;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void changeToolbar(Activity mActivity, TextView leftView, TextView titleView, TextView rightView,ImageView imageView) {
        leftView.setVisibility(View.INVISIBLE);
        titleView.setText("审批");
        rightView.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
    }
}
