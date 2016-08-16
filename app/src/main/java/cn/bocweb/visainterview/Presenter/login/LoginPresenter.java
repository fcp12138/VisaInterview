package cn.bocweb.visainterview.presenter.login;

import android.text.TextUtils;

import cn.bocweb.visainterview.config.NetConfig;
import cn.bocweb.visainterview.contract.login.LoginContract;
import cn.bocweb.visainterview.net.retrofit.RetrofitUtil;
import cn.bocweb.visainterview.net.retrofit.exception.ApiException;
import cn.bocweb.visainterview.net.retrofit.function.ResultFunc;
import cn.bocweb.visainterview.net.retrofit.model.CheckBean;
import cn.bocweb.visainterview.net.retrofit.model.LoginBean;
import cn.bocweb.visainterview.net.retrofit.response.LoginResponse;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 登录
 * Created by fcp on 2016/8/11.
 */
public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View mContractView;
    Subscription mSubscription;

    public LoginPresenter(LoginContract.View mContractView) {
        this.mContractView = mContractView;
    }

    @Override
    public void setContractView(LoginContract.View mContractView) {
        this.mContractView = mContractView;
    }

    @Override
    public void unbindSubscribe() {
        //取消
        if(mSubscription!=null)mSubscription.unsubscribe();
    }

    @Override
    public void login(String name, String pwd, String deviceCode) {
        if(TextUtils.isEmpty(NetConfig.BASE_URL)){
            mContractView.showMsg("请先进入设置页面进行网络配置");
        }else {
            mSubscription = RetrofitUtil.getService()
            .login(name,pwd,deviceCode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(new Func1<LoginResponse, LoginBean>() {
                @Override
                public LoginBean call(LoginResponse loginResponse) {
                    if(loginResponse == null || loginResponse.GetLoginResult == null){
                        throw new ApiException("未获取到数据");
                    }
                    return loginResponse.GetLoginResult;
                }
            })
            .subscribe(new Subscriber<LoginBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    mContractView.showMsg(e.getMessage());
                    mContractView.hideDialog();
                }

                @Override
                public void onNext(LoginBean loginBean) {
                    if (loginBean.getIsMatch().equals("否")) {
                        mContractView.showMsg("账号或用户名错误");
                    } else if (loginBean.getIsUnable().equals("设备未授权")) {
                        mContractView.showMsg("该设备尚未进行授权!");
                    } else if (loginBean.getIsUnable().equals("账号被禁用")) {
                        mContractView.showMsg("该账号已被禁止使用!");
                    } else {
                        mContractView.getLoginResult(loginBean);
                    }
                }
            });
        }

    }
}
