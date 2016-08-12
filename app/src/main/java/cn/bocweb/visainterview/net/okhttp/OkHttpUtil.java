package cn.bocweb.visainterview.net.okhttp;
import android.util.Log;

import java.io.File;

import cn.bocweb.visainterview.config.NetConfig;
import cn.bocweb.visainterview.net.okhttp.interceptor.CacheInterceptor;
import cn.bocweb.visainterview.net.okhttp.interceptor.TokenInterceptor;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OkHttp封装
 * Created by fcp on 2016/4/19.
 */
public class OkHttpUtil {

    private static OkHttpClient instance;

    /**
     * 获得Client单例
     */
    public static OkHttpClient getInstance(File cacheDirectory) {
        if (instance == null){
            synchronized (OkHttpUtil.class){
                if (instance == null){
                    OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
                    if (cacheDirectory != null) {
                        HttpLoggingInterceptor mLog = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                Log.d("OkHttp",message);
                            }
                        }).setLevel(HttpLoggingInterceptor.Level.BODY);
                        instance = mBuilder
                                .cache(new Cache(cacheDirectory, NetConfig.NET_CACHE_FILE_SIZE))
                                .addInterceptor(mLog)
                                .addInterceptor(new TokenInterceptor())
                                .addInterceptor(new CacheInterceptor())
                                .build();
                    }else {
                        instance = mBuilder.build();
                    }
                }
            }
        }
        return instance;
    }




}
