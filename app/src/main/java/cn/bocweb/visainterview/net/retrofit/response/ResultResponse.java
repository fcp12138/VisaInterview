package cn.bocweb.visainterview.net.retrofit.response;

/**
 * Object(本应用中未用到)
 * Created by fcp on 2016/8/11.
 */
public class ResultResponse<T> extends BaseResponse {

    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
