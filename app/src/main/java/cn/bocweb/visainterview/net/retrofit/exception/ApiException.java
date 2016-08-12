package cn.bocweb.visainterview.net.retrofit.exception;

/**
 * 异常
 * Created by fcp on 2016/8/12.
 */
public class ApiException extends RuntimeException{

    private int code = 0;
    private String message = "";

    public ApiException() {
    }

    public ApiException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
