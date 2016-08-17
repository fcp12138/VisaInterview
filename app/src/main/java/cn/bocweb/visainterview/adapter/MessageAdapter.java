package cn.bocweb.visainterview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bocweb.visainterview.R;
import cn.bocweb.visainterview.net.retrofit.model.NewsInfoBean;

/**
 * 消息列表适配器
 * Created by fcp on 2016/8/17.
 */
public class MessageAdapter extends SimpleAdapter<NewsInfoBean> {

    private boolean showflag = false;
    private ArrayList<NewsInfoBean> deleteList = new ArrayList<>();

    public MessageAdapter(Context context, ArrayList<NewsInfoBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_message_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final NewsInfoBean mNewsInfoBean = mDatas.get(position);
        String FID = mNewsInfoBean.getFID();
        String Ftype = mNewsInfoBean.getFType();
        String Fsubject = mNewsInfoBean.getFSubject();
        String Fcontent = mNewsInfoBean.getFContent();
        String FReleaseTime = mNewsInfoBean.getFReleaseTime();
        String Fread = mNewsInfoBean.getFRead();
        //填充数据
        viewHolder.mTvMessageFsubject.setText(Ftype);
        viewHolder.mTvMessageFcontent.setText(Fcontent);
        viewHolder.mTvMessageFReleaseTime.setText(FReleaseTime);
        //显示图片判断
        if (Ftype.equals("系统公告")) {
            viewHolder.mImgType.setImageResource(R.mipmap.notice_public);
        } else if (Ftype.equals("业务信息")) {
            viewHolder.mImgType.setImageResource(R.mipmap.notice_messag);
        }
        //是否浏览过
        if (Fread.equals("1")) {
            viewHolder.mTvMessageFread.setVisibility(View.GONE);
        } else if (Fread.equals("0")) {
            viewHolder.mTvMessageFread.setVisibility(View.VISIBLE);
        }
        //是否显示选择
        if (!showflag) {
            viewHolder.mCb.setVisibility(View.GONE);
        } else {
            viewHolder.mCb.setVisibility(View.VISIBLE);
            if(deleteList.contains(mNewsInfoBean)){
                viewHolder.mCb.setChecked(true);
            }else {
                viewHolder.mCb.setChecked(false);
            }
            viewHolder.mCb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(deleteList.contains(mNewsInfoBean)){
                        deleteList.remove(mNewsInfoBean);
                    }else {
                        deleteList.add(mNewsInfoBean);
                    }
                    if(mMessageListener!=null)mMessageListener.deleteItem(deleteList.size());
                }
            });
        }

        return convertView;
    }

    /**
     * 显示删除
     */
    public void showDeleteMode(){
        showflag = true;
        this.notifyDataSetChanged();
    }

    public void showNormalMode(){
        showflag = false;
        deleteList.clear();
        this.notifyDataSetChanged();
    }

    public int getDeleteSize(){
        return deleteList.size();
    }

    /**
     * 取消全选
     */
    public void cancelDelete(){
        deleteList.clear();
        this.notifyDataSetChanged();
        if(mMessageListener!=null)mMessageListener.deleteItem(deleteList.size());
    }

    /**
     * 全选
     */
    public void allDelete(){
        deleteList.clear();
        deleteList.addAll(mDatas);
        this.notifyDataSetChanged();
        if(mMessageListener!=null)mMessageListener.deleteItem(deleteList.size());
    }

    /**
     * 删除选中的
     */
    public void delete(){
        for(int i= 0 ;i<mDatas.size();i++){
            NewsInfoBean mNewsInfoBean = mDatas.get(i);
            if(deleteList.contains(mNewsInfoBean)){
                mDatas.remove(i);
                i--;
            }
        }
        deleteList.clear();
        notifyDataSetChanged();
    }

    public ArrayList<NewsInfoBean> getDeleteList() {
        return deleteList;
    }

    static class ViewHolder {
        @BindView(R.id.cb)
        CheckBox mCb;
        @BindView(R.id.img_type)
        ImageView mImgType;
        @BindView(R.id.tv_message_Fsubject)
        TextView mTvMessageFsubject;
        @BindView(R.id.img_message_enter)
        ImageView mImgMessageEnter;
        @BindView(R.id.tv_message_FReleaseTime)
        TextView mTvMessageFReleaseTime;
        @BindView(R.id.tv_message_Fread)
        TextView mTvMessageFread;
        @BindView(R.id.tv_message_Fcontent)
        TextView mTvMessageFcontent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    MessageListener mMessageListener;

    public void setmMessageListener(MessageListener mMessageListener) {
        this.mMessageListener = mMessageListener;
    }

    public interface MessageListener{
        void deleteItem(int number);
    }


}
