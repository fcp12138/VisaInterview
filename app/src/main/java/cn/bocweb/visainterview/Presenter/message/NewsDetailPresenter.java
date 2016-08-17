package cn.bocweb.visainterview.presenter.message;

import cn.bocweb.visainterview.contract.message.NewsDetailContract;
import cn.bocweb.visainterview.net.retrofit.RetrofitUtil;
import cn.bocweb.visainterview.net.retrofit.exception.ApiException;
import cn.bocweb.visainterview.net.retrofit.model.NewsDetailBean;
import cn.bocweb.visainterview.net.retrofit.response.NewsDetailResponse;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 消息信息详情
 * Created by fcp on 2016/8/17.
 */
public class NewsDetailPresenter implements NewsDetailContract.Presenter {

    NewsDetailContract.View mContractView;
    Subscription mSubscription;

    public NewsDetailPresenter(NewsDetailContract.View mContractView) {
        this.mContractView = mContractView;
    }

    @Override
    public void setContractView(NewsDetailContract.View mContractView) {
        this.mContractView = mContractView;
    }

    public void getDetail(String userid , String fnewsid){
        mSubscription = RetrofitUtil.getService()
                .getNewsDetail(userid,fnewsid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<NewsDetailResponse, NewsDetailBean>() {
                    @Override
                    public NewsDetailBean call(NewsDetailResponse mNewsDetailResponse) {
                        if(mNewsDetailResponse == null || mNewsDetailResponse.GetNewsDetail == null){
                            throw new ApiException("未获取到数据");
                        }
                        return mNewsDetailResponse.GetNewsDetail;
                    }
                })
                .subscribe(new Subscriber<NewsDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mContractView.showMsg(e.getMessage());
                        mContractView.getDetailError();
                    }

                    @Override
                    public void onNext(NewsDetailBean mNewsDetailBean) {
                        mContractView.getDetailResult(mNewsDetailBean);
                    }
                });
    }

    @Override
    public void unbindSubscribe() {
        if(mSubscription != null) mSubscription.unsubscribe();
    }
}
