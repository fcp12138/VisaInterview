package cn.bocweb.visainterview.net.okhttp.interceptor;

import java.io.IOException;

import cn.bocweb.visainterview.manager.MyApplication;
import cn.bocweb.visainterview.utils.NetWorkUtil;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存拦截
 * Created by fcp on 2016/6/7.
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //从链中获得原始请求
        Request originalRequest = chain.request();
        //获取retrofit @headers里面的参数，参数可以自己定义，在本例我自己定义的是cache，跟@headers里面对应就可以了
        String cache = originalRequest.header("cache");
        if(MyApplication.mMyApplication != null && !NetWorkUtil.isConnected(MyApplication.mMyApplication)){
            originalRequest  = originalRequest.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        //传递给下一个
        //return chain.proceed(originalRequest);
        Response originalResponse = chain.proceed(originalRequest);
        String cacheControl = originalResponse.header("Cache-Control");
        //如果cacheControl为空，就让他TIMEOUT_CONNECT秒的缓存，本例是5秒，方便观察。注意这里的cacheControl是服务器返回的
        if (cacheControl == null) {
            //如果cache没值，缓存时间为TIMEOUT_CONNECT，有的话就为cache的值
            if (cache == null || "".equals(cache)) {
                cache = "2419200";
            }
            originalResponse = originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + cache)
                    .build();
            return originalResponse;
        } else {
            return originalResponse;
        }
    }
}
