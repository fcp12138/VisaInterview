package com.jingchen.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 *  Created by cb on 2016/6/21.
 */
public class PullableRelLayout extends RelativeLayout implements Pullable{
    public PullableRelLayout(Context context) {
        super(context);
    }

    public PullableRelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableRelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean canPullDown()
    {
        if (getScrollY() == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean canPullUp()
    {
            return false;
    }
}
