package cn.bocweb.visainterview.ui.login;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bocweb.visainterview.R;
import cn.bocweb.visainterview.base.VIDialogActivity;
import cn.bocweb.visainterview.config.NetConfig;
import cn.bocweb.visainterview.contract.login.TestContract;
import cn.bocweb.visainterview.data.sp.SPConstants;
import cn.bocweb.visainterview.data.sp.SPUtils;
import cn.bocweb.visainterview.presenter.login.TestPresenter;
import cn.bocweb.visainterview.utils.LogUtils;
import cn.bocweb.visainterview.utils.PermissionUtils;
import cn.bocweb.visainterview.utils.ToastUtils;

public class InternetSetActivity extends VIDialogActivity implements TestContract.View {

    @BindView(R.id.set_net_ip_edit)
    EditText mNetIpEdit;
    @BindView(R.id.set_service_ip_edit)
    EditText mServiceIpEdit;
    @BindView(R.id.set_service_name_edit)
    EditText mServiceNameEdit;
    @BindView(R.id.set_net_save_btn)
    Button mNetSaveBtn;
    @BindView(R.id.set_copy_text)
    TextView mCopyText;

    TestContract.Presenter mTestPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mTestPresenter = new TestPresenter();
        mTestPresenter.setContractView(this);
        initBar();
        initView();
    }

    private void initBar() {
        getTitleText().setText("网络设置");
        getLeftBtn().setText("返回");
        getLeftBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        String url = SPUtils.getPrefString(this, SPConstants.URL, "");
        String net_ip = SPUtils.getPrefString(this, SPConstants.NET_IP, "");
        String service_ip = SPUtils.getPrefString(this, SPConstants.SERVICE_IP, "");
        String service_name = SPUtils.getPrefString(this, SPConstants.SERVICE_NAME, "");
        if (!url.equals("")) {
            mNetIpEdit.setText(net_ip);
            mServiceIpEdit.setText(service_ip);
            mServiceNameEdit.setText(service_name);
        }
    }

    @Override
    protected int setContentLayoutId() {
        return R.layout.activity_internet_set;
    }

    @OnClick({R.id.set_net_save_btn, R.id.set_copy_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_net_save_btn:
                mTestPresenter.test(mNetIpEdit.getText().toString(), mServiceIpEdit.getText().toString(), mServiceNameEdit.getText().toString());
                break;
            case R.id.set_copy_text:
                if(PermissionUtils.requestPermissionPhone(this)){
                    doCopy();
                }
                break;
        }
    }

    /**
     * 复制到剪切板
     */
    private void doCopy(){
        TelephonyManager tm = (TelephonyManager) InternetSetActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceCode = tm.getDeviceId();
        ClipboardManager cmb = (ClipboardManager) InternetSetActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData myClip;
        myClip = ClipData.newPlainText(deviceCode,"");
        cmb.setPrimaryClip(myClip);
        ToastUtils.showShort(this,"复制成功");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PermissionUtils.PERMISSIONS_REQUEST_PHONE && PermissionUtils.verifyPermissions(grantResults)){
            doCopy();
        }
    }



    @Override
    public void showMsg(String msg) {
        ToastUtils.showShort(this,msg);
        LogUtils.d(msg);
    }

    /**
     * 连接成功
     */
    @Override
    public void linkSuccess(String url,String net_ip,  String service_ip,  String service_name) {
        NetConfig.BASE_URL = url;
        SPUtils.setPrefString(this,SPConstants.URL, url);
        SPUtils.setPrefString(this,SPConstants.NET_IP, net_ip);
        SPUtils.setPrefString(this,SPConstants.SERVICE_IP, service_ip);
        SPUtils.setPrefString(this,SPConstants.SERVICE_NAME, service_name);
        ToastUtils.showShort(this,"服务器连接成功");
        //关闭
        finish();
    }

    @Override
    public void showDialog(String str) {
        showProgress(str);
    }

    @Override
    public void hideDialog() {
        hideProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTestPresenter.unbindSubscribe();
    }
}
