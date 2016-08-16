package cn.bocweb.visainterview.ui.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bocweb.visainterview.R;
import cn.bocweb.visainterview.base.VIDialogActivity;
import cn.bocweb.visainterview.contract.login.SysInfoContract;
import cn.bocweb.visainterview.contract.login.VersionContract;
import cn.bocweb.visainterview.contract.login.LoginContract;
import cn.bocweb.visainterview.data.sp.SPConstants;
import cn.bocweb.visainterview.data.sp.SPUtils;
import cn.bocweb.visainterview.net.retrofit.model.CheckBean;
import cn.bocweb.visainterview.net.retrofit.model.LoginBean;
import cn.bocweb.visainterview.net.retrofit.model.SysInfoBean;
import cn.bocweb.visainterview.presenter.login.CheckPresenter;
import cn.bocweb.visainterview.presenter.login.LoginPresenter;
import cn.bocweb.visainterview.presenter.login.SysInfoPresenter;
import cn.bocweb.visainterview.service.UpdataService;
import cn.bocweb.visainterview.ui.MainActivity;
import cn.bocweb.visainterview.utils.PasswordTransformation;
import cn.bocweb.visainterview.utils.PermissionUtils;
import cn.bocweb.visainterview.utils.ToastUtils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends VIDialogActivity implements LoginContract.View, VersionContract.View, SysInfoContract.View {

    LoginContract.Presenter mPresenter;
    VersionContract.Presenter mCheckPresenter;
    SysInfoContract.Presenter mSysInfoPresenter;

    @BindView(R.id.login_account_img)
    ImageView mAccountImg;
    @BindView(R.id.login_account_et)
    EditText mAccountEt;
    @BindView(R.id.login_account_layout)
    RelativeLayout mAccountLayout;
    @BindView(R.id.login_pwd_img)
    ImageView mPwdImg;
    @BindView(R.id.login_pwd_et)
    EditText mPwdEt;
    @BindView(R.id.login_pwd_layout)
    RelativeLayout mPwdLayout;
    @BindView(R.id.login_remember_cb)
    CheckBox mRememberCb;
    @BindView(R.id.login_remember_layout)
    RelativeLayout mRememberLayout;
    @BindView(R.id.login_set_text)
    TextView mSetText;
    @BindView(R.id.login_set_layout)
    RelativeLayout mSetLayout;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.login_version)
    TextView mVersion;

    boolean isMemory;
    String MobileNumber;//后台获得的设备号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this);
        mCheckPresenter = new CheckPresenter(this);
        mSysInfoPresenter = new SysInfoPresenter(this);
        initView();
    }

    private void initView() {
        mPwdEt.setTransformationMethod(new PasswordTransformation());
        mAccountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    mAccountLayout.setBackgroundResource(R.drawable.bg_edit_transparent);
                    mAccountImg.setVisibility(View.VISIBLE);
                } else {
                    mAccountLayout.setBackgroundResource(R.drawable.bg_edit_white);
                    mAccountImg.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPwdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    mPwdLayout.setBackgroundResource(R.drawable.bg_edit_transparent);
                    mPwdImg.setVisibility(View.VISIBLE);
                } else {
                    mPwdLayout.setBackgroundResource(R.drawable.bg_edit_white);
                    mPwdImg.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        isMemory = SPUtils.getPrefBoolean(this, SPConstants.IS_MEMORY, false);
        if (isMemory) {
            mRememberCb.setChecked(true);
            mAccountEt.setText(SPUtils.getPrefString(this,SPConstants.NAME,""));
            mPwdEt.setText(SPUtils.getPrefString(this,SPConstants.PWD,""));
        }
    }

    @OnClick({R.id.login_set_layout, R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_set_layout:
                startActivity(new Intent(LoginActivity.this, InternetSetActivity.class));
                break;
            case R.id.login_btn:
                //先检测版本
                mCheckPresenter.check("android");
                break;
        }
    }

    private void doLogin() {
        if(PermissionUtils.requestPermissionPhone(this)){
            TelephonyManager tm = (TelephonyManager) LoginActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
            String deviceCode = tm.getDeviceId();
            changeText("登录中");
            mPresenter.login(mAccountEt.getText().toString(), mPwdEt.getText().toString(), deviceCode);
        }
    }

    @Override
    public void showMsg(String str) {
        ToastUtils.showShort(this,str);
    }

    @Override
    public void hideDialog() {
        hideProgress();
    }

    @Override
    public void showDialog(String str) {
        showProgress(str);
    }

    /**
     *  检测返回结果
     */
    @Override
    public void setCheckResult(final CheckBean mCheckBean) {
        String versionname = getAppVersionName(this);
        //如果版本不一样
        if (!versionname.equals(mCheckBean.getUpdateversion())) {
            if (mCheckBean.getUpdatetype().equals("不需要更新")) {
                doLogin();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setCancelable(false);
                builder.setMessage(mCheckBean.getUpdatenote());
                builder.setTitle("更新提示");
                builder.setPositiveButton(mCheckBean.getUpdatetype(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                            field.setAccessible(true);
                            field.set(dialog, true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        startService(new Intent(LoginActivity.this, UpdataService.class).putExtra("url",mCheckBean.getUpdatesite()));
                        dialog.dismiss();
                    }

                });
                builder.setNegativeButton("忽略", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mCheckBean.getUpdatetype().equals("强制更新")) {
                            showMsg("必须更新");
                            try {
                                Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                                field.setAccessible(true);
                                //设置mShowing值，欺骗android系统
                                field.set(dialog, false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else{
                            dialog.dismiss();
                            doLogin();
                        }
                    }
                });
                builder.create().show();
            }
        } else{
            doLogin();
        }
    }

    /**
     * 登录结果返回
     */
    @Override
    public void getLoginResult(LoginBean loginBean) {
        SPUtils.setPrefString(this,SPConstants.USER_ID,loginBean.getUserID());
        MobileNumber = loginBean.getMobileNumber();
        // 获取系统配置
        changeText("加载后台配置中");
        mSysInfoPresenter.getSysInfo();
    }

    /**
     * 获得系统配置返回
     */
    @Override
    public void getSysInfo(SysInfoBean mSysInfoBean) {
        //检测
        /*if(mSysInfoBean.getMobilePhoneBind() != null && mSysInfoBean.getMobilePhoneBind().equals("是")){
            if(!((TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId().equals(MobileNumber) ){
                showMsg("当前手机号和系统登记的不符！");
                hideDialog();
                return;
            }
        }*/
        //保存配置
        SharedPreferences.Editor mEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        mEditor.putString(SPConstants.AllowStopGPS, mSysInfoBean.getAllowStopGPS());
        mEditor.putInt(SPConstants.GPSInterval, mSysInfoBean.getGPSInterval());
        mEditor.putString(SPConstants.MobilePhoneBind, mSysInfoBean.getMobilePhoneBind());
        mEditor.putInt(SPConstants.PicHeight, mSysInfoBean.getPicHeight());
        mEditor.putInt(SPConstants.PicMaxSizel, mSysInfoBean.getPicMaxSizel());
        mEditor.putInt(SPConstants.PicMaxSum, mSysInfoBean.getPicMaxSum());
        mEditor.putInt(SPConstants.PicMinSum, mSysInfoBean.getPicMinSum());
        mEditor.putInt(SPConstants.VideoMaxSizel, mSysInfoBean.getVideoMaxSizel());
        mEditor.putInt(SPConstants.VideoMaxSum, mSysInfoBean.getVideoMaxSum());
        mEditor.putInt(SPConstants.VideoMinSum, mSysInfoBean.getVideoMinSum());
        mEditor.putInt(SPConstants.FBillListItems, mSysInfoBean.getFBillListItems());
        mEditor.putInt(SPConstants.FBillDetailItems, mSysInfoBean.getFBillDetailItems());
        mEditor.putString(SPConstants.FBillFilter, mSysInfoBean.getFBillFilter());
        mEditor.putString(SPConstants.WaterMarked, mSysInfoBean.getWaterMarked());
        mEditor.putString(SPConstants.WaterMarkFontize, mSysInfoBean.getWaterMarkFontSize());
        mEditor.putString(SPConstants.WaterMarkFontColor, mSysInfoBean.getWaterMarkFontColor());
        //是否要保存密码
        if (mRememberCb.isChecked()) {
            mEditor.putString(SPConstants.NAME, mAccountEt.getText().toString());
            mEditor.putString( SPConstants.PWD, mPwdEt.getText().toString());
            mEditor.putBoolean( SPConstants.IS_MEMORY, true);
        } else {
            mEditor.putBoolean( SPConstants.IS_MEMORY, false);
        }
        mEditor.apply();
        //登录主界面
        hideDialog();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     *  返回键监听
     */
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
                this.finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PermissionUtils.PERMISSIONS_REQUEST_PHONE && PermissionUtils.verifyPermissions(grantResults)){
            TelephonyManager tm = (TelephonyManager) LoginActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
            String deviceCode = tm.getDeviceId();
            mPresenter.login(mAccountEt.getText().toString(), mPwdEt.getText().toString(), deviceCode);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbindSubscribe();
        mCheckPresenter.unbindSubscribe();
        mSysInfoPresenter.unbindSubscribe();
    }
}

