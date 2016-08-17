package cn.bocweb.visainterview.presenter.message;

import java.util.ArrayList;
import java.util.List;

import cn.bocweb.visainterview.adapter.MessageAdapter;
import cn.bocweb.visainterview.contract.message.NewsListContract;
import cn.bocweb.visainterview.net.retrofit.RetrofitUtil;
import cn.bocweb.visainterview.net.retrofit.exception.ApiException;
import cn.bocweb.visainterview.net.retrofit.model.NewsInfoBean;
import cn.bocweb.visainterview.net.retrofit.response.NewsListResponse;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 消息列表
 * Created by fcp on 2016/8/17.
 */
public class NewsListPresenter implements NewsListContract.Presenter{

    NewsListContract.View mContractView;
    Subscription mSubscription;
    private String UserID;
    private int FPage = 1;
    private int FPageSize = 10;
    private ArrayList<NewsInfoBean> mArrayList ;
    private MessageAdapter mMessageAdapter;

    public NewsListPresenter(NewsListContract.View mContractView, String UserID , MessageAdapter mMessageAdapter) {
        this.mContractView = mContractView;
        this.UserID = UserID;
        this.mMessageAdapter = mMessageAdapter;
        mArrayList = mMessageAdapter.getmDatas();
        FPage = 1;
        FPageSize = 10;
    }

    @Override
    public void setContractView(NewsListContract.View mContractView) {
        this.mContractView = mContractView;
    }

    @Override
    public void unbindSubscribe() {
        if(mSubscription != null) mSubscription.unsubscribe();
    }

    @Override
    public void getNewsList() {
        mSubscription = RetrofitUtil.getService()
                .getNewsList( UserID,  FPage,  FPageSize )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<NewsListResponse, List<NewsInfoBean>>() {
                    @Override
                    public List<NewsInfoBean> call(NewsListResponse mNewsListResponse) {
                        if(mNewsListResponse == null
                                || mNewsListResponse.GetNewsList == null
                                || mNewsListResponse.GetNewsList.NewsInfo == null){
                            throw new ApiException("未获取到数据");
                        }
                        return mNewsListResponse.GetNewsList.NewsInfo;
                    }
                })
                .subscribe(new Subscriber<List<NewsInfoBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(mArrayList.size() == 0){
                            mContractView.loadFirstFail();
                        }else {
                            mContractView.loadError();
                        }
                    }

                    @Override
                    public void onNext(List<NewsInfoBean> mList) {
                        FPage++;
                        if(mArrayList.size() == 0){
                            mArrayList.addAll(mList);
                            mMessageAdapter.notifyDataSetChanged();
                            mContractView.loadFirstSuccess();
                        }else {
                            mArrayList.addAll(mList);
                            mMessageAdapter.notifyDataSetChanged();
                            mContractView.loadSuccess();
                        }
                    }
                });
    }

    @Override
    public void doRefresh() {
        mSubscription = RetrofitUtil.getService()
                .getNewsList( UserID,  1,  FPageSize )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<NewsListResponse, List<NewsInfoBean>>() {
                    @Override
                    public List<NewsInfoBean> call(NewsListResponse mNewsListResponse) {
                        if(mNewsListResponse == null
                                || mNewsListResponse.GetNewsList == null
                                || mNewsListResponse.GetNewsList.NewsInfo == null){
                            throw new ApiException("未获取到数据");
                        }
                        return mNewsListResponse.GetNewsList.NewsInfo;
                    }
                })
                .subscribe(new Subscriber<List<NewsInfoBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mContractView.refreshError();
                    }

                    @Override
                    public void onNext(List<NewsInfoBean> mList) {
                        FPage++;
                        mArrayList.clear();
                        mArrayList.addAll(mList);
                        mMessageAdapter.getDeleteList().clear();//删除选择的数据
                        mMessageAdapter.notifyDataSetChanged();
                        mContractView.refreshSuccess();
                    }
                });
    }
}
