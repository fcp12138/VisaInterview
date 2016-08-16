package cn.bocweb.visainterview.manager;

import android.app.Application;

import cn.bocweb.visainterview.config.NetConfig;
import cn.bocweb.visainterview.data.sp.SPConstants;
import cn.bocweb.visainterview.data.sp.SPUtils;
import cn.bocweb.visainterview.net.retrofit.RetrofitUtil;


/**
 * 应用
 * Created by fcp on 2016/7/28.
 */
public class MyApplication extends Application {

    public static MyApplication mMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyApplication = this;
        //设置基本信息
        NetConfig.BASE_URL = SPUtils.getPrefString(this, SPConstants.URL,"");
        //Retrofit
        RetrofitUtil.init(getApplicationContext());
    }
}
