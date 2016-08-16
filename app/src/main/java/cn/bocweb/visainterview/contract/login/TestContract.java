package cn.bocweb.visainterview.contract.login;

import cn.bocweb.visainterview.presenter.BasePresenter;
import cn.bocweb.visainterview.contract.BaseView;

/**
 * 测试
 * Created by fcp on 2016/8/15.
 */
public interface TestContract {

    interface View extends BaseView {

        void showMsg(String msg);

        void linkSuccess(String url, String net_ip,  String service_ip,  String service_name);

        void showDialog(String str);

        void hideDialog();
    }

    interface Presenter extends BasePresenter<View> {

        void test(String net_ip,String service_ip,String service_name);
    }

}
