package cn.bocweb.visainterview.net.retrofit.service;

import cn.bocweb.visainterview.net.retrofit.response.CheckResponse;
import cn.bocweb.visainterview.net.retrofit.response.LoginResponse;
import cn.bocweb.visainterview.net.retrofit.response.SysInfoResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 面签接口
 * Created by fcp on 2016/7/28.
 */
public interface VIService {

    /**
     * 检测
     */
    @GET("HelloWorld")
    Observable<String> test();

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("GetLoginResult")
    Observable<LoginResponse> login(@Field("username") String username, @Field("password") String password, @Field("devicetoken") String devicetoken);


    /**
     * 检测版本
     */
    @FormUrlEncoded
    @POST("CheckVersion")
    Observable<CheckResponse> checkVersion(@Field("devicetype") String type);

    /**
     * 加载后台配置
     */
    @GET("GetSysInfo")
    Observable<SysInfoResponse> getSysInfo();

}
