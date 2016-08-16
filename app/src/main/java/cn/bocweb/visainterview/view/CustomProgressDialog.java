package cn.bocweb.visainterview.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import cn.bocweb.visainterview.R;

/**
 * 自定义progressDialog
 *
 * Created by ljh on 2015/11/18.
 */
public class CustomProgressDialog extends Dialog {
    private TextView mTextView;

    public CustomProgressDialog(Context context, String strMessage, Activity activity) {
        this(context, R.style.dialog, strMessage,activity);
    }

    public CustomProgressDialog(Context context, int theme, String strMessage, Activity activity) {
        super(context, theme);
        this.setContentView(R.layout.dialog_loading);
        final DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha =  0.8f;
        lp.width = dm.widthPixels * 6 / 11;
        lp.height = dm.heightPixels * 1 / 4;
        lp.dimAmount=0.5f;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        mTextView = (TextView) this.findViewById(R.id.id_tv_loadingmsg);
        if (mTextView != null) {
            mTextView.setText(strMessage);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            dismiss();
        }
    }

    public void changeText(String text){
        if(mTextView!=null){
            mTextView.setText(text);
        }
    }


}
