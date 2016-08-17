package cn.bocweb.visainterview.contract.message;

import cn.bocweb.visainterview.contract.BaseView;
import cn.bocweb.visainterview.presenter.BasePresenter;

/**
 * 消息列表
 * Created by fcp on 2016/8/17.
 */
public interface NewsListContract {

    interface View extends BaseView {

        void showMsg(String str);

        void refreshError();

        void loadError();

        void loadSuccess();

        void refreshSuccess();

        void loadFirstSuccess();

        void loadFirstFail();
    }

    interface Presenter extends BasePresenter<View> {

        void getNewsList();

        void doRefresh();
    }

}
