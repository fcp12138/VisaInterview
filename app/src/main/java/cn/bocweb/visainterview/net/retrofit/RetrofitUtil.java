package cn.bocweb.visainterview.net.retrofit;

import android.content.Context;

import java.io.File;

import cn.bocweb.visainterview.config.NetConfig;
import cn.bocweb.visainterview.net.okhttp.OkHttpUtil;
import cn.bocweb.visainterview.net.retrofit.service.VIService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工具类
 * Created by fcp on 2016/6/7.
 */
public class RetrofitUtil {

    private static File mCacheDir;//缓存目录
    public static Retrofit mRetrofit;
    public static VIService mVIService;

    public static void init(Context mContext) {
        //初始化OkHttpClient并设置缓存目录
        File mDir = mContext.getExternalCacheDir();
        mCacheDir = new File(mDir, NetConfig.NET_CACHE_FILE_NAME);
    }

    /**
     * 获得Retrofit单例
     */
    public static Retrofit getInstance() {
        if (mRetrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (mRetrofit == null) {
                    Retrofit.Builder mBuilder = new Retrofit.Builder();
                    if (mCacheDir != null) {
                        mBuilder.client(OkHttpUtil.getInstance(mCacheDir));
                    }
                    mRetrofit = mBuilder
                            .baseUrl(NetConfig.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return mRetrofit;
    }

    public static <T> T create(Class<T> service) {
        return getInstance().create(service);
    }

    public static VIService getService(){
        if (mVIService == null) {
            synchronized (RetrofitUtil.class) {
                if (mVIService == null) {
                    mVIService = create(VIService.class);
                }
            }
        }
        return mVIService;
    }


}
