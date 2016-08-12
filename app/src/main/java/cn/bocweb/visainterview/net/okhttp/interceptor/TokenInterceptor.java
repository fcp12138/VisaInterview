package cn.bocweb.visainterview.net.okhttp.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * token拦截器
 * Created by fcp on 2016/6/14.
 */
public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //从链中获得原始请求
        Request originalRequest = chain.request();
        /*if(MyApplication.mMyApplication!=null){
            String id = Settings.Secure.getString(MyApplication.mMyApplication.getContentResolver(), Settings.Secure.ANDROID_ID);
            if(id!=null && !id.isEmpty()){
                originalRequest = originalRequest.newBuilder().addHeader("token",id ).build();
            }
        }*/
        return chain.proceed(originalRequest);
    }
}
