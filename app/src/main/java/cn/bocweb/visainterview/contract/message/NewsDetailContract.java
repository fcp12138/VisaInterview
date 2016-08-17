package cn.bocweb.visainterview.contract.message;

import cn.bocweb.visainterview.contract.BaseView;
import cn.bocweb.visainterview.net.retrofit.model.NewsDetailBean;
import cn.bocweb.visainterview.presenter.BasePresenter;

/**
 * 消息详情
 * Created by fcp on 2016/8/17.
 */
public interface NewsDetailContract {

    interface View extends BaseView {

        void showMsg(String str);

        void getDetailResult(NewsDetailBean mNewsDetailBean);

        void getDetailError();

    }

    interface Presenter extends BasePresenter<View> {

    }


}
