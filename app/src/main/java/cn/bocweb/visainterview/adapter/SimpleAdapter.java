package cn.bocweb.visainterview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * 一般适配器
 * Created by fcp on 2016/8/17.
 */
public abstract class SimpleAdapter<T> extends BaseAdapter{

    protected LayoutInflater mLayoutInflater;
    protected ArrayList<T> mDatas;
    protected Context mContext;

    public SimpleAdapter(Context context,ArrayList<T> datas) {
        mLayoutInflater = LayoutInflater.from(context);
        mDatas = datas;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public ArrayList<T> getmDatas() {
        return mDatas;
    }
}
