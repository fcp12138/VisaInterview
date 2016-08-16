package cn.bocweb.visainterview.presenter.login;

import cn.bocweb.visainterview.contract.login.VersionContract;
import cn.bocweb.visainterview.net.retrofit.RetrofitUtil;
import cn.bocweb.visainterview.net.retrofit.exception.ApiException;
import cn.bocweb.visainterview.net.retrofit.function.ResultFunc;
import cn.bocweb.visainterview.net.retrofit.model.CheckBean;
import cn.bocweb.visainterview.net.retrofit.response.CheckResponse;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 检测版本
 * Created by fcp on 2016/8/16.
 */
public class CheckPresenter implements VersionContract.Presenter{

    VersionContract.View mContractView ;
    Subscription mSubscription;

    public CheckPresenter(VersionContract.View mContractView) {
        this.mContractView = mContractView;
    }

    @Override
    public void check(String type) {
        mContractView.showDialog("检查版本更新中");
        mSubscription = RetrofitUtil.getService()
                .checkVersion(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<CheckResponse, CheckBean>() {
                    @Override
                    public CheckBean call(CheckResponse checkResponse) {
                        if(checkResponse == null || checkResponse.CheckVersion == null){
                            throw new ApiException("未获取到数据");
                        }
                        return checkResponse.CheckVersion;
                    }
                })
                .subscribe(new Subscriber<CheckBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mContractView.showMsg(e.getMessage());
                        mContractView.hideDialog();
                    }

                    @Override
                    public void onNext(CheckBean mCheckBean) {
                        mContractView.setCheckResult(mCheckBean);
                    }
                });
    }

    @Override
    public void setContractView(VersionContract.View mContractView) {
        this.mContractView = mContractView;
    }

    @Override
    public void unbindSubscribe() {
        if(mSubscription!= null )mSubscription.unsubscribe();
    }
}
