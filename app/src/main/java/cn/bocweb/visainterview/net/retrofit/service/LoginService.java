package cn.bocweb.visainterview.net.retrofit.service;

import cn.bocweb.visainterview.net.retrofit.model.LoginBean;
import cn.bocweb.visainterview.net.retrofit.response.ResultResponse;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 登录
 * Created by fcp on 2016/7/28.
 */
public interface LoginService {

    @POST("GetLoginResult")
    Observable<ResultResponse<LoginBean>> login();

}
