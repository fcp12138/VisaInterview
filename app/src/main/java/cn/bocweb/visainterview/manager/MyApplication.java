package cn.bocweb.visainterview.manager;

import android.app.Application;

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
        //Retrofit
        RetrofitUtil.init(getApplicationContext());
    }
}
