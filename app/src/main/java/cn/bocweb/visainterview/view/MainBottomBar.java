package cn.bocweb.visainterview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import cn.bocweb.visainterview.R;

/**
 * 主界面下栏
 * Created by fcp on 2016/8/16.
 */
public class MainBottomBar extends LinearLayout implements View.OnClickListener {

    ImageView mBottomMassageImg;
    TextView mBottomMassageText;
    ImageView mBottomApproveImg;
    TextView mBottomApproveText;
    ImageView mBottomApplicationImg;
    TextView mBottomApplicationText;
    ImageView mBottomMineImg;
    TextView mBottomMineText;
    LinearLayout mBottomMassageLayout;
    LinearLayout mBottomApproveLayout;
    LinearLayout mBottomApplicationLayout;
    LinearLayout mBottomMineLayout;

    public MainBottomBar(Context context) {
        this(context, null);
    }

    public MainBottomBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainBottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.layout_main_bottom, this, true);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mBottomMassageImg = (ImageView) findViewById(R.id.bottom_massage_img);
        mBottomMassageText = (TextView) findViewById(R.id.bottom_massage_text);
        mBottomApproveImg = (ImageView) findViewById(R.id.bottom_approve_img);
        mBottomApproveText = (TextView) findViewById(R.id.bottom_approve_text);
        mBottomApplicationImg = (ImageView) findViewById(R.id.bottom_application_img);
        mBottomApplicationText = (TextView) findViewById(R.id.bottom_application_text);
        mBottomMineImg = (ImageView) findViewById(R.id.bottom_mine_img);
        mBottomMineText = (TextView) findViewById(R.id.bottom_mine_text);
        mBottomMassageLayout = (LinearLayout) findViewById(R.id.bottom_massage_layout);
        mBottomApproveLayout = (LinearLayout) findViewById(R.id.bottom_approve_layout);
        mBottomApplicationLayout = (LinearLayout) findViewById(R.id.bottom_application_layout);
        mBottomMineLayout = (LinearLayout) findViewById(R.id.bottom_mine_layout);
        mBottomMassageLayout.setOnClickListener(this);
        mBottomApproveLayout.setOnClickListener(this);
        mBottomApplicationLayout.setOnClickListener(this);
        mBottomMineLayout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_massage_layout:
                mBottomMassageText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_color));
                mBottomApproveText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_no_color));
                mBottomApplicationText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_no_color));
                mBottomMineText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_no_color));
                bottomClickListener.clickMessage();
                break;
            case R.id.bottom_approve_layout:
                mBottomMassageText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_no_color));
                mBottomApproveText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_color));
                mBottomApplicationText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_no_color));
                mBottomMineText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_no_color));
                bottomClickListener.clickApprove();
                break;
            case R.id.bottom_application_layout:
                mBottomMassageText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_no_color));
                mBottomApproveText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_no_color));
                mBottomApplicationText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_color));
                mBottomMineText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_no_color));
                bottomClickListener.clickApplication();
                break;
            case R.id.bottom_mine_layout:
                mBottomMassageText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_no_color));
                mBottomApproveText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_no_color));
                mBottomApplicationText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_no_color));
                mBottomMineText.setTextColor(getResources().getColor(R.color.main_bottom_bar_select_color));
                bottomClickListener.clickMine();
                break;
        }
    }

    BottomClickListener bottomClickListener = new BottomClickListener() {
        @Override
        public void clickMessage() {

        }

        @Override
        public void clickApprove() {

        }

        @Override
        public void clickApplication() {

        }

        @Override
        public void clickMine() {

        }
    };

    public void setBottomClickListener(BottomClickListener bottomClickListener) {
        this.bottomClickListener = bottomClickListener;
    }

    public interface BottomClickListener{
        void clickMessage();
        void clickApprove();
        void clickApplication();
        void clickMine();

    }
}
