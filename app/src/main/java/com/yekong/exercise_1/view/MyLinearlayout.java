package com.yekong.exercise_1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by xigua on 2017/9/4.
 */

public class MyLinearlayout extends LinearLayout {
    public MyLinearlayout(Context context) {
        super(context);
    }

    public MyLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("MyLinearlayout", "onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("MyLinearlayout", "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.e("MyLinearlayout", "onScrollChanged");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("MyLinearlayout", "onDraw");
    }

}
