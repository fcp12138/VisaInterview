package cn.bocweb.visainterview.net.retrofit.model;

/**
 * 消息详情
 * Created by fcp on 2016/8/17.
 */
public class NewsDetailBean {

    /**
     * FType : 系统公告
     * FSubject : 放假通知
     * FContent : 元旦放假三天，从2016.1.1至2016.1.3！
     * FReleaseTime : 2015/11/22 14:20:25
     * FName : 黄菲菲
     */

    private String FType;
    private String FSubject;
    private String FContent;
    private String FReleaseTime;
    private String FName;

    public void setFType(String FType) {
        this.FType = FType;
    }

    public void setFSubject(String FSubject) {
        this.FSubject = FSubject;
    }

    public void setFContent(String FContent) {
        this.FContent = FContent;
    }

    public void setFReleaseTime(String FReleaseTime) {
        this.FReleaseTime = FReleaseTime;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getFType() {
        return FType;
    }

    public String getFSubject() {
        return FSubject;
    }

    public String getFContent() {
        return FContent;
    }

    public String getFReleaseTime() {
        return FReleaseTime;
    }

    public String getFName() {
        return FName;
    }
}
