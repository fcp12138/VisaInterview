package cn.bocweb.visainterview.net.retrofit.function;

import cn.bocweb.visainterview.net.retrofit.exception.ApiException;
import cn.bocweb.visainterview.net.retrofit.response.ResultResponse;
import rx.functions.Func1;

/**
 * 统一的预处理接口
 * Created by fcp on 2016/8/12.
 */
public class ResultFunc<T> implements Func1<ResultResponse<T>, T> {
    @Override
    public T call(ResultResponse<T> tResultResponse) {
        if(tResultResponse == null){
            throw new ApiException();
        }else if (tResultResponse.getError_code() != 0) {
            throw new ApiException(tResultResponse.getError_code(),tResultResponse.getReason());
        }
        return tResultResponse.getResult();
    }
}
