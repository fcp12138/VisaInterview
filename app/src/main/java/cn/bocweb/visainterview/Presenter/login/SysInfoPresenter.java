package cn.bocweb.visainterview.presenter.login;

import cn.bocweb.visainterview.contract.login.SysInfoContract;
import cn.bocweb.visainterview.net.retrofit.RetrofitUtil;
import cn.bocweb.visainterview.net.retrofit.exception.ApiException;
import cn.bocweb.visainterview.net.retrofit.function.ResultFunc;
import cn.bocweb.visainterview.net.retrofit.model.LoginBean;
import cn.bocweb.visainterview.net.retrofit.model.SysInfoBean;
import cn.bocweb.visainterview.net.retrofit.response.SysInfoResponse;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *  系统后台配置
 * Created by fcp on 2016/8/16.
 */
public class SysInfoPresenter implements SysInfoContract.Presenter{

    private Subscription mSubscription;
    private SysInfoContract.View mContractView;

    public SysInfoPresenter(SysInfoContract.View mContractView) {
        this.mContractView = mContractView;
    }

    @Override
    public void setContractView(SysInfoContract.View mContractView) {
        this.mContractView = mContractView;
    }



    @Override
    public void unbindSubscribe() {
        if(mSubscription!=null)mSubscription.unsubscribe();
    }

    @Override
    public void getSysInfo() {
        mSubscription = RetrofitUtil.getService()
                .getSysInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<SysInfoResponse, SysInfoBean>() {
                    @Override
                    public SysInfoBean call(SysInfoResponse sysInfoResponse) {
                        if(sysInfoResponse == null || sysInfoResponse.GetSysInfo == null){
                            throw new ApiException("未获取到数据");
                        }
                        return sysInfoResponse.GetSysInfo;
                    }
                })
                .subscribe(new Subscriber<SysInfoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mContractView.showMsg(e.getMessage());
                        mContractView.hideDialog();
                    }

                    @Override
                    public void onNext(SysInfoBean mSysInfoBean) {
                        mContractView.getSysInfo(mSysInfoBean);
                    }
                });
    }
}
