package cn.bocweb.visainterview.contract.login;

import cn.bocweb.visainterview.contract.BasePresenter;
import cn.bocweb.visainterview.contract.BaseView;

/**
 * 登录
 * Created by fcp on 2016/8/11.
 */
public interface LoginContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

        void login();
    }

}
