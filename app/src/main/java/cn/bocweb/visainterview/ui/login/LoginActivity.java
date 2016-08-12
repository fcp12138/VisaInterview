package cn.bocweb.visainterview.ui.login;

import android.os.Bundle;

import butterknife.OnClick;
import cn.bocweb.visainterview.R;
import cn.bocweb.visainterview.base.VIActivity;
import cn.bocweb.visainterview.contract.login.LoginContract;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends VIActivity implements LoginContract.View {

    LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTitleText().setText("登录");
        mPresenter.setContractView(this);
    }

    @Override
    protected int setContentLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.login_btn)
    public void onClick() {
        mPresenter.login();
    }
}

