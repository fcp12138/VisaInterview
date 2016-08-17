package cn.bocweb.visainterview.ui.fragment;

import cn.bocweb.visainterview.view.CustomProgressDialog;

/**
 *
 * Created by fcp on 2016/8/17.
 */
public abstract class DialogFragment extends MainFragment {

    private CustomProgressDialog progressDialog;

    public void showProgress(String text) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
        progressDialog = new CustomProgressDialog(getContext(), text ,getActivity());
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
            progressDialog = new CustomProgressDialog(getContext(), text ,getActivity());
            progressDialog.setCanceledOnTouchOutside(false);
        }else {
            progressDialog.changeText(text);
        }
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }

    }

}
