package cn.bocweb.visainterview.contract.login;

import cn.bocweb.visainterview.contract.BaseView;
import cn.bocweb.visainterview.net.retrofit.model.CheckBean;
import cn.bocweb.visainterview.presenter.BasePresenter;

/**
 * 检测版本
 * Created by fcp on 2016/8/16.
 */
public interface VersionContract {

    interface View extends BaseView {

        void showMsg(String str);

        void hideDialog();

        void showDialog(String str);

        void setCheckResult(CheckBean mCheckBean);
    }

    interface Presenter extends BasePresenter<View> {

        void check(String type);
    }
}
