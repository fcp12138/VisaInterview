package cn.bocweb.visainterview.net.retrofit.model;

/**
 * 单条消息
 * Created by fcp on 2016/8/17.
 */
public class NewsInfoBean {

    String FID	;//消息内码
    String FType;//	消息类别
    String FSubject;//消息主题
    String FContent;//消息正文	datetime
    String FReleaseTime	;//消息发布时间	string
    String FRead;//消息是否已读	datetime

    public String getFID() {
        return FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public String getFType() {
        return FType;
    }

    public void setFType(String FType) {
        this.FType = FType;
    }

    public String getFSubject() {
        return FSubject;
    }

    public void setFSubject(String FSubject) {
        this.FSubject = FSubject;
    }

    public String getFContent() {
        return FContent;
    }

    public void setFContent(String FContent) {
        this.FContent = FContent;
    }

    public String getFReleaseTime() {
        return FReleaseTime;
    }

    public void setFReleaseTime(String FReleaseTime) {
        this.FReleaseTime = FReleaseTime;
    }

    public String getFRead() {
        return FRead;
    }

    public void setFRead(String FRead) {
        this.FRead = FRead;
    }
}
