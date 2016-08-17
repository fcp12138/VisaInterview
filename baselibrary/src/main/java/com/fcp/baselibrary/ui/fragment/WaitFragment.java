package com.fcp.baselibrary.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.fcp.baselibrary.R;

/**
 * 等待Fragment
 * Created by fcp on 2016/8/17.
 */
public abstract class WaitFragment extends BaseFragment {

    private RelativeLayout progressLayout;
    private RelativeLayout failLayout;
    private View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout mFrameLayout = (FrameLayout) inflater.inflate(R.layout.base_fragment_wait_layout,container,false);
        contentView = inflater.inflate(setContentLayoutId(),container,false);
        mFrameLayout.addView(contentView,0);
        return mFrameLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressLayout = (RelativeLayout) view.findViewById(R.id.base_fragment_wait_progress_layout);
        failLayout = (RelativeLayout) view.findViewById(R.id.base_fragment_wait_fail_layout);
    }


    protected void showFileLayout(){
        progressLayout.setVisibility(View.GONE);
        failLayout.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
    }

    protected void showContentLayout(){
        progressLayout.setVisibility(View.GONE);
        failLayout.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
    }

    protected void showProgressLayout(){
        progressLayout.setVisibility(View.VISIBLE);
        failLayout.setVisibility(View.GONE);
        contentView.setVisibility(View.GONE);
    }

    protected abstract int setContentLayoutId();

}
