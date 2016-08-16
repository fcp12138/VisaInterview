package cn.bocweb.visainterview.net.retrofit.model;

/**
 * 加载后台配置bean
 */
public class SysInfoBean {
    /**
     * MobilePhoneBind : 是
     * AllowStopGPS : 否
     * GPSInterval : 600
     * PicHeight : 800
     * PicMaxSizel : 512
     * PicMaxSum : 20
     * PicMinSum : 2
     * VideoMaxSizel : 51200
     * VideoMaxSum : 20
     * VideoMinSum : 2
     */

    private String AllowStopGPS;    //是否允许手动中断定位
    private int GPSInterval;    //GPS扫描时间间隔
    private String MobilePhoneBind;  //是否绑定手机号进行登录
    private int PicHeight;//照片缩放后的高度(像素)
    private int PicMaxSizel;   //照片允许最大几K
    private int PicMaxSum; //照片允许最多几张
    private int PicMinSum; //照片允许最少几张
    private int VideoMaxSizel;//视频允许最大几K
    private int VideoMaxSum;  //视频允许最多几个
    private int VideoMinSum; //视频允许最少几个
    private int FBillListItems;//业务项目-可见动态行数
    private int FBillDetailItems;//业务详情-可见动态行数
    private String FBillFilter;//业务详情-过滤条件

    private String WaterMarked; //是否加水印
    private String WaterMarkFontSize; //水印字体大小
    private String WaterMarkFontColor; //水印字体颜色


    public String getAllowStopGPS() {
        return AllowStopGPS;
    }

    public void setAllowStopGPS(String allowStopGPS) {
        AllowStopGPS = allowStopGPS;
    }

    public int getGPSInterval() {
        return GPSInterval;
    }

    public void setGPSInterval(int GPSInterval) {
        this.GPSInterval = GPSInterval;
    }

    public String getMobilePhoneBind() {
        return MobilePhoneBind;
    }

    public void setMobilePhoneBind(String mobilePhoneBind) {
        MobilePhoneBind = mobilePhoneBind;
    }

    public int getPicHeight() {
        return PicHeight;
    }

    public void setPicHeight(int picHeight) {
        PicHeight = picHeight;
    }

    public int getPicMaxSizel() {
        return PicMaxSizel;
    }

    public void setPicMaxSizel(int picMaxSizel) {
        PicMaxSizel = picMaxSizel;
    }

    public int getPicMaxSum() {
        return PicMaxSum;
    }

    public void setPicMaxSum(int picMaxSum) {
        PicMaxSum = picMaxSum;
    }

    public int getPicMinSum() {
        return PicMinSum;
    }

    public void setPicMinSum(int picMinSum) {
        PicMinSum = picMinSum;
    }

    public int getVideoMaxSizel() {
        return VideoMaxSizel;
    }

    public void setVideoMaxSizel(int videoMaxSizel) {
        VideoMaxSizel = videoMaxSizel;
    }

    public int getVideoMaxSum() {
        return VideoMaxSum;
    }

    public void setVideoMaxSum(int videoMaxSum) {
        VideoMaxSum = videoMaxSum;
    }

    public int getVideoMinSum() {
        return VideoMinSum;
    }

    public void setVideoMinSum(int videoMinSum) {
        VideoMinSum = videoMinSum;
    }

    public int getFBillListItems() {
        return FBillListItems;
    }

    public void setFBillListItems(int FBillListItems) {
        this.FBillListItems = FBillListItems;
    }

    public int getFBillDetailItems() {
        return FBillDetailItems;
    }

    public void setFBillDetailItems(int FBillDetailItems) {
        this.FBillDetailItems = FBillDetailItems;
    }

    public String getFBillFilter() {
        return FBillFilter;
    }

    public void setFBillFilter(String FBillFilter) {
        this.FBillFilter = FBillFilter;
    }

    public String getWaterMarked() {
        return WaterMarked;
    }

    public void setWaterMarked(String waterMarked) {
        WaterMarked = waterMarked;
    }

    public String getWaterMarkFontSize() {
        return WaterMarkFontSize;
    }

    public void setWaterMarkFontSize(String waterMarkFontSize) {
        WaterMarkFontSize = waterMarkFontSize;
    }

    public String getWaterMarkFontColor() {
        return WaterMarkFontColor;
    }

    public void setWaterMarkFontColor(String waterMarkFontColor) {
        WaterMarkFontColor = waterMarkFontColor;
    }
}
