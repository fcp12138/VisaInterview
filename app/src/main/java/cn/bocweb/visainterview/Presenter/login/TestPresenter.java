package cn.bocweb.visainterview.presenter.login;

import cn.bocweb.visainterview.contract.login.TestContract;
import cn.bocweb.visainterview.net.retrofit.function.ResultFunc;
import cn.bocweb.visainterview.net.retrofit.model.LoginBean;
import cn.bocweb.visainterview.net.retrofit.service.VIService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 测试接口
 * Created by fcp on 2016/8/15.
 */
public class TestPresenter implements TestContract.Presenter{

    private TestContract.View mContractView;
    private Subscription mSubscription;
    private String url;

    @Override
    public void test(final String net_ip, final String service_ip, final String service_name) {
        mContractView.showDialog("测试配置是否正确");
        //创建Retrofit
        url = "http://" + net_ip + ":" + service_ip + "/" + service_name + ".asmx/";
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mSubscription = mRetrofit.create(VIService.class)
                .test()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mContractView.showMsg(e.getMessage());
                        mContractView.hideDialog();
                    }

                    @Override
                    public void onNext(String s) {
                        if(s.equals("Hello World")){
                            mContractView.linkSuccess(url,net_ip,service_ip,service_name);
                        }else {
                            mContractView.showMsg("服务器连接失败，请检查设置是否正确");
                        }
                        mContractView.hideDialog();
                    }

                });
    }

    @Override
    public void setContractView(TestContract.View mContractView) {
        this.mContractView = mContractView;
    }

    @Override
    public void unbindSubscribe() {
        if(mSubscription!= null )mSubscription.unsubscribe();
    }
}
