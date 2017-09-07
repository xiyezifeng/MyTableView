package com.yekong.exercise_1.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by xigua on 2017/9/4.
 */

public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        Log.e("MyViewGroup", "onlayout        :        " + b + "     " + i  + "     " + i1  + "     " + i2  + "     " + i3  );
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("MyViewGroup", "onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("MyViewGroup", "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }


}
