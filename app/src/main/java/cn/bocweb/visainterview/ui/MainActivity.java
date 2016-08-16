package cn.bocweb.visainterview.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bocweb.visainterview.R;
import cn.bocweb.visainterview.base.VIActivity;
import cn.bocweb.visainterview.ui.login.fragment.ApplicationFragment;
import cn.bocweb.visainterview.ui.login.fragment.ApproveFragment;
import cn.bocweb.visainterview.ui.login.fragment.MessageFragment;
import cn.bocweb.visainterview.ui.login.fragment.MineFragment;
import cn.bocweb.visainterview.view.MainBottomBar;

public class MainActivity extends VIActivity {

    @BindView(R.id.main_bottom_bar)
    MainBottomBar mMainBottomBar;
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;
    //四个Fragment
    MessageFragment mMessageFragment;
    ApproveFragment mApproveFragment;
    ApplicationFragment mApplicationFragment;
    MineFragment mMineFragment;
    //当前选项index
    private int selectIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        //得到FragmentManager对象
        fragmentManager = this.getSupportFragmentManager();
        //历史保存
        if(savedInstanceState != null){
            selectIndex = savedInstanceState.getInt("selectIndex");
            mMessageFragment = (MessageFragment) fragmentManager.findFragmentByTag("mMessageFragment");
            mApproveFragment = (ApproveFragment) fragmentManager.findFragmentByTag("mApproveFragment");
            mApplicationFragment = (ApplicationFragment) fragmentManager.findFragmentByTag("mApplicationFragment");
            mMineFragment = (MineFragment) fragmentManager.findFragmentByTag("mMineFragment");
        }
        //显示默认项
        setTabSelection(selectIndex);
    }

    @Override
    protected int setContentLayoutId() {
        return R.layout.activity_main;
    }

    private void initView() {
        mMainBottomBar.setBottomClickListener(new BottomBarListener());
        getLeftBtn().setVisibility(View.INVISIBLE);

    }

    private void setTabSelection(int index) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //隐藏所有
        hideAllFragment(transaction);
        switch (index){
            case 0://消息界面
                getTitleText().setText("消息");
                if (mMessageFragment == null) {
                    // 如果clientFragment为空，则创建一个并添加到界面上
                    mMessageFragment = new MessageFragment();
//                    Bundle mBundle = new Bundle();
//                    mBundle.putInt(GOODSID,goodsId);
//                    mMessageFragment.setArguments(mBundle);
                    transaction.add(R.id.main_content_layout, mMessageFragment, "mMessageFragment");
                } else {
                    // 如果clientFragment不为空，则直接将它显示出来
                    transaction.show(mMessageFragment);
                }
                break;
            case 1:
                getTitleText().setText("审批");
                if (mApproveFragment == null) {
                    // 如果clientFragment为空，则创建一个并添加到界面上
                    mApproveFragment = new ApproveFragment();
                    transaction.add(R.id.main_content_layout, mApproveFragment, "mApproveFragment");
                } else {
                    // 如果clientFragment不为空，则直接将它显示出来
                    transaction.show(mApproveFragment);
                }
                break;
            case 2:
                getTitleText().setText("应用");
                if (mApplicationFragment == null) {
                    // 如果clientFragment为空，则创建一个并添加到界面上
                    mApplicationFragment = new ApplicationFragment();
                    transaction.add(R.id.main_content_layout, mApplicationFragment, "mApplicationFragment");
                } else {
                    // 如果clientFragment不为空，则直接将它显示出来
                    transaction.show(mApplicationFragment);
                }
                break;
            case 3:
                getTitleText().setText("我的");
                if (mMineFragment == null) {
                    // 如果clientFragment为空，则创建一个并添加到界面上
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.main_content_layout, mMineFragment, "mMineFragment");
                } else {
                    // 如果clientFragment不为空，则直接将它显示出来
                    transaction.show(mMineFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction){
        if(mMessageFragment != null){
            transaction.hide(mMessageFragment);
        }
        if(mApproveFragment != null){
            transaction.hide(mApproveFragment);
        }
        if(mApplicationFragment != null){
            transaction.hide(mApplicationFragment);
        }
        if(mMineFragment != null){
            transaction.hide(mMineFragment);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class BottomBarListener implements MainBottomBar.BottomClickListener {

        @Override
        public void clickMessage() {
            setTabSelection(0);
        }

        @Override
        public void clickApprove() {
            setTabSelection(1);
        }

        @Override
        public void clickApplication() {
            setTabSelection(2);
        }

        @Override
        public void clickMine() {
            setTabSelection(3);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("selectIndex", selectIndex);
        super.onSaveInstanceState(outState);

    }
}
