package cn.bocweb.visainterview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * 简单通用的适配器
 * Created by fcp on 2015/12/11.
 */
public abstract class CommonAdapter<T> extends BaseAdapter{


    protected Context mContext;
    protected ArrayList<T> models;
    protected LayoutInflater mLayoutInflater;
    protected final int mItemLayoutId;

    public CommonAdapter(Context context, ArrayList<T> models , int itemLayoutId) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.models = models;
        this.mItemLayoutId = itemLayoutId;

    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public T getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    /**
     * 子类只需实现此方法
     * @param viewHolder ViewHolder
     * @param item 对应数据
     */
    public abstract void convert(ViewHolder viewHolder, T item);

    private ViewHolder getViewHolder(int position, View convertView,ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
    }


}
