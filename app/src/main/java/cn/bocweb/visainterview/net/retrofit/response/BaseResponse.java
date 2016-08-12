package cn.bocweb.visainterview.net.retrofit.response;

/**
 * 基本返参
 * Created by fcp on 2016/5/3.
 */
public abstract class BaseResponse {

    private int error_code;
    private String reason;


    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
/*
http://121.199.40.253/ca2016/group
*/

/*{"code":0,"message":"success","value":
[{"name":"A",
"teams": [
{"name":"Colombia","win":1,"draw":0,"lost":0,"goal":2,"fumble":0,"point":3},
{"name":"Costa_Rica","win":0,"draw":1,"lost":0,"goal":0,"fumble":0,"point":1},
{"name":"Paraguay","win":0,"draw":1,"lost":0,"goal":0,"fumble":0,"point":1},
{"name":"USA","win":0,"draw":0,"lost":1,"goal":0,"fumble":2,"point":0}
]},
{"name":"B",
"teams":[
{"name":"Peru","win":1,"draw":0,"lost":0,"goal":1,"fumble":0,"point":3},
{"name":"Brazil","win":0,"draw":1,"lost":0,"goal":0,"fumble":0,"point":1},
{"name":"Ecuador","win":0,"draw":1,"lost":0,"goal":0,"fumble":0,"point":1},
{"name":"Haiti","win":0,"draw":0,"lost":1,"goal":0,"fumble":1,"point":0}
]},
{"name":"C",
"teams":[
{"name":"Jamaica","win":0,"draw":0,"lost":0,"goal":0,"fumble":0,"point":0},
{"name":"Mexico","win":0,"draw":0,"lost":0,"goal":0,"fumble":0,"point":0},
{"name":"Uruguay","win":0,"draw":0,"lost":0,"goal":0,"fumble":0,"point":0},
{"name":"Venezuela","win":0,"draw":0,"lost":0,"goal":0,"fumble":0,"point":0}
]},
{"name":"D",
"teams":[
{"name":"Argentina","win":0,"draw":0,"lost":0,"goal":0,"fumble":0,"point":0},
{"name":"Panama","win":0,"draw":0,"lost":0,"goal":0,"fumble":0,"point":0},
{"name":"Chile","win":0,"draw":0,"lost":0,"goal":0,"fumble":0,"point":0},
{"name":"Bolivia","win":0,"draw":0,"lost":0,"goal":0,"fumble":0,"point":0}
]}
]}

*/