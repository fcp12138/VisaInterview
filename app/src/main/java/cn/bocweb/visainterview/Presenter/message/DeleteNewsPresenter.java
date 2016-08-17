package cn.bocweb.visainterview.presenter.message;

import java.util.ArrayList;

import cn.bocweb.visainterview.contract.message.DeleteNewsContract;
import cn.bocweb.visainterview.net.retrofit.RetrofitUtil;
import cn.bocweb.visainterview.net.retrofit.exception.ApiException;
import cn.bocweb.visainterview.net.retrofit.model.DeleteNewsBean;
import cn.bocweb.visainterview.net.retrofit.model.NewsInfoBean;
import cn.bocweb.visainterview.net.retrofit.response.DeleteNewsResponse;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 删除消息
 * Created by fcp on 2016/8/17.
 */
public class DeleteNewsPresenter implements DeleteNewsContract.Presenter{

    DeleteNewsContract.View mContractView;
    Subscription mSubscription;

    public DeleteNewsPresenter(DeleteNewsContract.View mContractView) {
        this.mContractView = mContractView;
    }

    @Override
    public void setContractView(DeleteNewsContract.View mContractView) {
        this.mContractView = mContractView;
    }

    @Override
    public void delete(ArrayList<NewsInfoBean> mArrayList , String userId){
        mContractView.showDialog("删除中");
        StringBuilder mStringBuffer = new StringBuilder();
        for(NewsInfoBean mNewsInfoBean :mArrayList ){
            mStringBuffer.append(mNewsInfoBean.getFID()).append(",");
        }
        mStringBuffer.substring(0, mStringBuffer.length() - 1);
        mSubscription = RetrofitUtil.getService()
                .deleteNews(mStringBuffer.toString(),userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<DeleteNewsResponse, DeleteNewsBean>() {
                    @Override
                    public DeleteNewsBean call(DeleteNewsResponse mDeleteNewsResponse) {
                        if(mDeleteNewsResponse == null || mDeleteNewsResponse.DeleteNews == null){
                            throw new ApiException("未获取到数据");
                        }
                        return mDeleteNewsResponse.DeleteNews;
                    }
                })
                .subscribe(new Subscriber<DeleteNewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mContractView.hideDialog();
                        mContractView.showMsg(e.getMessage());
                    }

                    @Override
                    public void onNext(DeleteNewsBean FResult) {
                        if (FResult.getFResult().equals("成功")) {
                            mContractView.delete();
                            mContractView.hideDialog();
                        }
                        else {
                            mContractView.showMsg("删除失败");
                            mContractView.hideDialog();
                        }
                    }
                });
    }

    @Override
    public void unbindSubscribe() {
        if( mSubscription!= null)mSubscription.unsubscribe();
    }
}
