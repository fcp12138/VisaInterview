package cn.bocweb.visainterview.contract.login;

import cn.bocweb.visainterview.contract.BaseView;
import cn.bocweb.visainterview.net.retrofit.model.SysInfoBean;
import cn.bocweb.visainterview.presenter.BasePresenter;

/**
 * 后台系统
 * Created by fcp on 2016/8/16.
 */
public interface SysInfoContract {

    interface View extends BaseView {

        void showMsg(String str);

        void hideDialog();

        void getSysInfo(SysInfoBean mSysInfoBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getSysInfo();

    }
}
