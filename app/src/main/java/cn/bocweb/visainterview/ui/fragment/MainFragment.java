package cn.bocweb.visainterview.ui.fragment;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fcp.baselibrary.ui.fragment.WaitFragment;

/**
 * 主页的四个fragment
 * Created by fcp on 2016/8/17.
 */
public abstract class MainFragment extends WaitFragment {

    public void changeToolbar(Activity mActivity , TextView leftView , TextView titleView,TextView rightView,ImageView imageView){
        leftView.setVisibility(View.INVISIBLE);
        titleView.setVisibility(View.INVISIBLE);
        rightView.setVisibility(View.INVISIBLE);
    }


}
