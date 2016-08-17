package cn.bocweb.visainterview.ui.message;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bocweb.visainterview.R;
import cn.bocweb.visainterview.base.VIActivity;
import cn.bocweb.visainterview.base.VIDialogActivity;
import cn.bocweb.visainterview.contract.message.NewsDetailContract;
import cn.bocweb.visainterview.data.sp.SPConstants;
import cn.bocweb.visainterview.data.sp.SPUtils;
import cn.bocweb.visainterview.net.retrofit.model.NewsDetailBean;
import cn.bocweb.visainterview.presenter.message.NewsDetailPresenter;
import cn.bocweb.visainterview.utils.ToastUtils;

/**
 * 消息中心（详细信息）
 * Created by fcp on 2016/8/17.
 */
public class MessageDetailActivity extends VIDialogActivity implements NewsDetailContract.View {
    public static final String FNEWSID = "fnewsid";

    @BindView(R.id.tv_msgdetail_FType)
    TextView mTvMsgdetailFType;
    @BindView(R.id.tv_msgdetail_FSubject)
    TextView mTvMsgdetailFSubject;
    @BindView(R.id.tv_msgdetail_FReleaseTime)
    TextView mTvMsgdetailFReleaseTime;
    @BindView(R.id.tv_msgdetail_FContent)
    TextView mTvMsgdetailFContent;

    @Override
    protected int setContentLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getTitleText().setText("消息详情");
        showProgress("数据加载中");
        NewsDetailPresenter mNewsDetailPresenter = new NewsDetailPresenter(this);
        String USER_ID = SPUtils.getPrefString(this, SPConstants.USER_ID,"");
        String fnewsid = getIntent().getStringExtra(FNEWSID);
        mNewsDetailPresenter.getDetail(USER_ID,fnewsid);
    }

    @Override
    public void showMsg(String str) {
        ToastUtils.showShort(this,str);
    }

    @Override
    public void getDetailResult(NewsDetailBean mNewsDetailBean) {
        mTvMsgdetailFType.setText(mNewsDetailBean.getFType() + ":");
        mTvMsgdetailFReleaseTime.setText(mNewsDetailBean.getFReleaseTime());
        mTvMsgdetailFContent.setText(mNewsDetailBean.getFContent());
        mTvMsgdetailFSubject.setText(mNewsDetailBean.getFSubject());
        hideProgress();
    }

    @Override
    public void getDetailError() {
        hideProgress();
    }
}
