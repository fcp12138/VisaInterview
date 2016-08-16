package cn.bocweb.visainterview.net.retrofit.model;

/**
 * 检查更新类
 */
public class CheckBean {


    /**
     * updatetype : 强制更新
     * updatesite : http://shouji.360tpcdn.com/160111/ac9fbd0564b343b188163f6ccae13a6c/cn.bocweb.visainterview_6.apk
     * updatetime : 2016/1/6 0:00:00
     * updatenote : 1、即拍即传：现场拍摄完马上进行上传处理，办公更高效！
     2、GPS定位：每张照片和视频都带有相应的GPS定位地址！
     3、业务轨迹：业务人员上门阶段的定位轨迹图，一目了然！
     4、移动考勤：移动互联时代，移动考勤显得不拘一格，更方便！
     * updateversion : 6.0
     */

    private String updatetype;
    private String updatesite;
    private String updatetime;
    private String updatenote;
    private String updateversion;

    public String getUpdatetype() {
        return updatetype;
    }

    public void setUpdatetype(String updatetype) {
        this.updatetype = updatetype;
    }

    public String getUpdatesite() {
        return updatesite;
    }

    public void setUpdatesite(String updatesite) {
        this.updatesite = updatesite;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdatenote() {
        return updatenote;
    }

    public void setUpdatenote(String updatenote) {
        this.updatenote = updatenote;
    }

    public String getUpdateversion() {
        return updateversion;
    }

    public void setUpdateversion(String updateversion) {
        this.updateversion = updateversion;
    }
}
