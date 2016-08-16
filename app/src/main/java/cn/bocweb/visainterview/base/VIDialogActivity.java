package cn.bocweb.visainterview.base;

import cn.bocweb.visainterview.view.CustomProgressDialog;

/**
 * 有等待条的Activity
 * Created by fcp on 2016/8/16.
 */
public class VIDialogActivity extends VIActivity{

    private CustomProgressDialog progressDialog;

    public void showProgress(String text) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
        progressDialog = new CustomProgressDialog(this, text ,this);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
    }

    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }

    public void changeText(String text){
        if(progressDialog == null){
            progressDialog = new CustomProgressDialog(this, text ,this);
            progressDialog.setCanceledOnTouchOutside(false);
        }else {
            progressDialog.changeText(text);
        }
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }

    }

}
