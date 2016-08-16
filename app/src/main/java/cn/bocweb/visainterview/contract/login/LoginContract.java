package cn.bocweb.visainterview.contract.login;

import cn.bocweb.visainterview.net.retrofit.model.LoginBean;
import cn.bocweb.visainterview.presenter.BasePresenter;
import cn.bocweb.visainterview.contract.BaseView;

/**
 * 登录
 * Created by fcp on 2016/8/11.
 */
public interface LoginContract {

    interface View extends BaseView {

        void showMsg(String str);

        void hideDialog();

        void getLoginResult(LoginBean loginBean);
    }

    interface Presenter extends BasePresenter<View> {

        void login(String name, String Pwd, String deviceCode);
    }

}
