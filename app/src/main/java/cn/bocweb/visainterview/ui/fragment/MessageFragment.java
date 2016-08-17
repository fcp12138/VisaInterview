package cn.bocweb.visainterview.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.PullableListView;

import java.util.ArrayList;

import cn.bocweb.visainterview.R;
import cn.bocweb.visainterview.adapter.MessageAdapter;
import cn.bocweb.visainterview.contract.message.DeleteNewsContract;
import cn.bocweb.visainterview.contract.message.NewsListContract;
import cn.bocweb.visainterview.data.sp.SPConstants;
import cn.bocweb.visainterview.data.sp.SPUtils;
import cn.bocweb.visainterview.net.retrofit.model.NewsInfoBean;
import cn.bocweb.visainterview.presenter.message.DeleteNewsPresenter;
import cn.bocweb.visainterview.presenter.message.NewsListPresenter;
import cn.bocweb.visainterview.ui.message.MessageDetailActivity;
import cn.bocweb.visainterview.utils.ToastUtils;

/**
 * 消息
 * Created by fcp on 2016/8/16.
 */
public class MessageFragment extends DialogFragment implements NewsListContract.View, PullToRefreshLayout.OnPullListener,
        MessageAdapter.MessageListener, View.OnClickListener, AdapterView.OnItemClickListener, DeleteNewsContract.View {

    private NewsListContract.Presenter mPresenter;
    private DeleteNewsContract.Presenter mDeletePresenter;
    private PullableListView mListView;
    private MessageAdapter messageAdapter;
    private ArrayList<NewsInfoBean> mArrayList = new ArrayList<>();
    private PullToRefreshLayout mSwipeRefreshLayout;
    private LinearLayout mLinearLayout;
    private TextView mAllView;
    private TextView mDeleteView;

    @Override
    protected int setContentLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化
        mLinearLayout = (LinearLayout) view.findViewById(R.id.message_menu_layout);
        mAllView = (TextView) view.findViewById(R.id.message_menu_all);
        mDeleteView = (TextView) view.findViewById(R.id.message_menu_delete);
        //列表
        mSwipeRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.message_refresh_layout);
        mListView = (PullableListView) view.findViewById(R.id.message_list);
        messageAdapter = new MessageAdapter(view.getContext(),mArrayList);
        messageAdapter.setmMessageListener(this);
        mListView.setAdapter(messageAdapter);
        mSwipeRefreshLayout.setOnPullListener(this);
        mListView.setOnItemClickListener(this);
        //删除按钮
        mDeletePresenter = new DeleteNewsPresenter(this);
        mDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = SPUtils.getPrefString(getContext(),SPConstants.USER_ID,"");
                mDeletePresenter.delete(messageAdapter.getDeleteList() ,userId );
            }
        });
        mAllView.setOnClickListener(this);
        //加载初始数据
        String UserID = SPUtils.getPrefString(view.getContext(), SPConstants.USER_ID,"");
        mPresenter = new NewsListPresenter(this,UserID,messageAdapter);
        mPresenter.getNewsList();
    }

    @Override
    public void changeToolbar(Activity mActivity, TextView leftView, TextView titleView, final TextView rightView , final ImageView imageView) {
        leftView.setVisibility(View.INVISIBLE);
        titleView.setText("消息");
        rightView.setVisibility(View.INVISIBLE);
        rightView.setText("取消");
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.mipmap.midden);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//显示删除
                mLinearLayout.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                rightView.setVisibility(View.VISIBLE);
                messageAdapter.showDeleteMode();
            }
        });
        rightView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinearLayout.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                rightView.setVisibility(View.GONE);
                messageAdapter.showNormalMode();
            }
        });
    }

    @Override
    public void showMsg(String str) {
        ToastUtils.showShort(getContext(),str);
    }

    @Override
    public void hideDialog() {
        hideProgress();
    }

    /**
     * 删除成功后
     */
    @Override
    public void delete() {
        messageAdapter.delete();
    }

    @Override
    public void showDialog(String str) {
        showProgress(str);
    }

    @Override
    public void refreshError() {
        mSwipeRefreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
    }

    @Override
    public void loadError() {
        mSwipeRefreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
    }

    @Override
    public void loadSuccess() {
        if(messageAdapter.getDeleteSize() == messageAdapter.getCount()){
            mAllView.setText("取消全选");
        }else {
            mAllView.setText("全选");
        }
        mSwipeRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void refreshSuccess() {
        deleteItem(messageAdapter.getDeleteSize());//刷新控件
        mSwipeRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void loadFirstSuccess() {
        showContentLayout();
    }

    @Override
    public void loadFirstFail() {
        showFileLayout();
    }

    /**
     * 刷新
     */
    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        mPresenter.doRefresh();
    }

    /**
     * 加载
     */
    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        mPresenter.getNewsList();
    }

    /**
     * 选择点击删除
     */
    @Override
    public void deleteItem(int number) {
        if(number > 0){
            mDeleteView.setText("删除"+"("+number+")");
        }else {
            mDeleteView.setText("删除");
        }
        if(number == messageAdapter.getCount()){
            mAllView.setText("取消全选");
        }else {
            mAllView.setText("全选");
        }
    }

    /**
     * 点击全选时
     */
    @Override
    public void onClick(View v) {
        if(messageAdapter.getDeleteSize() == messageAdapter.getCount()){
            messageAdapter.cancelDelete();
        }else {
            messageAdapter.allDelete();
        }
    }

    /**
     * 选项点击
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), MessageDetailActivity.class);
        intent.putExtra(MessageDetailActivity.FNEWSID,mArrayList.get(position).getFID());
        mArrayList.get(position).setFRead("1");
        startActivity(intent);
    }
}
