package cn.bocweb.visainterview.ui.login.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fcp.baselibrary.ui.fragment.BaseFragment;

import cn.bocweb.visainterview.R;

/**
 * 应用界面
 * Created by fcp on 2016/8/16.
 */
public class ApplicationFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_application,container,false);
        initView(mView);
        return mView;
    }

    private void initView(View mView) {

    }

}
