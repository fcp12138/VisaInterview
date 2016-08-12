package cn.bocweb.visainterview.contract.login;

import cn.bocweb.visainterview.net.retrofit.RetrofitUtil;
import cn.bocweb.visainterview.net.retrofit.function.ResultFunc;
import cn.bocweb.visainterview.net.retrofit.model.LoginBean;
import cn.bocweb.visainterview.net.retrofit.service.LoginService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 登录
 * Created by fcp on 2016/8/11.
 */
public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View mContractView;

    @Override
    public void setContractView(LoginContract.View mContractView) {
        this.mContractView = mContractView;
    }

    @Override
    public void login() {
        Subscription mSubscription = RetrofitUtil.create(LoginService.class)
            .login()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(new ResultFunc<LoginBean>())
            .subscribe(new Subscriber<LoginBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(LoginBean loginBean) {

                }
            });
        //mSubscription.unsubscribe();取消
    }
}
