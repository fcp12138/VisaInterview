package cn.bocweb.visainterview.config;

/**
 * 网络配置
 * Created by fcp on 2016/8/11.
 */
public class NetConfig {

    /**
     * 网络地址(根据配置界面更改)
     */
    public static String BASE_URL = "http://122.224.166.154:8090/TageFund.asmx/";

    /**
     * 网络缓存文件名,getExternalCacheDir()路径下
     */
    public static final String NET_CACHE_FILE_NAME = "net-cache";

    /**
     * 网络缓存文件大小
     */
    public static final int NET_CACHE_FILE_SIZE = 10 * 1024 * 1024; // 10 MiB File

}
