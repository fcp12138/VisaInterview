package cn.bocweb.visainterview.contract.message;

import java.util.ArrayList;

import cn.bocweb.visainterview.contract.BaseView;
import cn.bocweb.visainterview.net.retrofit.model.NewsInfoBean;
import cn.bocweb.visainterview.presenter.BasePresenter;

/**
 * 删除
 * Created by fcp on 2016/8/17.
 */
public interface DeleteNewsContract {

    interface View extends BaseView {

        void showMsg(String str);

        void hideDialog();

        void delete();

        void showDialog(String str);
    }

    interface Presenter extends BasePresenter<View> {

        void delete(ArrayList<NewsInfoBean> deleteList, String userId);
    }
}
