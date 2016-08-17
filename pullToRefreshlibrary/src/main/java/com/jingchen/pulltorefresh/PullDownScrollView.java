package com.jingchen.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 *  Created by cb on 2016/6/22.
 */
public class PullDownScrollView extends ScrollView implements Pullable {

    public PullDownScrollView(Context context) {
        super(context);
    }

    public PullDownScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullDownScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canPullDown() {
        if (getScrollY() == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean canPullUp() {
        return false;
    }

}