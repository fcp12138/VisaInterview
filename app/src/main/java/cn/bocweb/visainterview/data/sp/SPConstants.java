package cn.bocweb.visainterview.data.sp;

/**
 * 用于记录PreferenceUtils存储的变量
 */
public class SPConstants {

    //基本配置
    public static final String URL = "URL";
    public static final String NET_IP = "net_ip";
    public static final String SERVICE_IP = "service_ip";
    public static final String SERVICE_NAME = "service_name";
    //用户登录操作
    public static final String USER_ID = "UserID";
    public static final String IS_MEMORY = "isMemory";//是否记住密码
    //public static final String MOBILE_NUMBER = "MobileNumber";//后台记录的设备号
    public static final String NAME = "name";
    public static final String PWD = "pwd";
    //加载后台配置
    public static final String AllowStopGPS = "AllowStopGPS";//是否允许手动中断定位
    public static final String GPSInterval = "GPSInterval";//GPS扫描时间间隔
    public static final String MobilePhoneBind = "MobilePhoneBind";//是否绑定手机号进行登录
    public static final String PicHeight = "PicHeight";//照片缩放后的高度(像素)
    public static final String PicMaxSizel = "PicMaxSizel";//照片允许最大几K
    public static final String PicMaxSum = "PicMaxSum";//照片允许最多几张
    public static final String PicMinSum = "PicMinSum";//照片允许最少几张
    public static final String VideoMaxSizel = "VideoMaxSizel";//视频允许最大几K
    public static final String VideoMaxSum = "VideoMaxSum";//视频允许最多几个
    public static final String VideoMinSum = "VideoMinSum";//视频允许最少几个
    public static final String FBillListItems = "FBillListItems";//业务项目-可见动态行数
    public static final String FBillDetailItems = "FBillDetailItems";//业务详情-可见动态行数
    public static final String FBillFilter = "FBillFilter";//业务详情-过滤条件 “客户姓名,业务部门,信贷专员”
    public static final String WaterMarked = "WaterMarked";//是否需要添加水印
    public static final String WaterMarkFontize = "WaterMarkFontize";//添加水印的字体大小
    public static final String WaterMarkFontColor = "WaterMarkFontColor";//添加水印的字体颜色


}
