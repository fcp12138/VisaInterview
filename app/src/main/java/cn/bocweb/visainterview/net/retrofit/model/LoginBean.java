package cn.bocweb.visainterview.net.retrofit.model;

/**
 * 登录
 * Created by fcp on 2016/7/28.
 */
public class LoginBean {

    String IsMatch;
    String IsUnable;
    String MobileNumber;
    String UserID;

    public String getIsMatch() {
        return IsMatch;
    }

    public void setIsMatch(String isMatch) {
        IsMatch = isMatch;
    }

    public String getIsUnable() {
        return IsUnable;
    }

    public void setIsUnable(String isUnable) {
        IsUnable = isUnable;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

}
