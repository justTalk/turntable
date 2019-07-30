package com.mm.turntable;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by Liu On 2019/7/27
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class ScrollerView extends LinearLayout {
    private static final String TAG = "ScrollerView";
    private int mLastX;
    private int mLastY;

    Scroller mScroller = new Scroller(getContext(), new Interpolator() {
        @Override
        public float getInterpolation(float input) {
            return input;
        }
    });

    public ScrollerView(Context context) {
        super(context);
    }

    public ScrollerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScrollerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                //scrollTo(-(int) event.getX() + getFirstChildWidth() / 2, -(int) event.getY() + getFirstChildHeight() / 2);
                break;
            case MotionEvent.ACTION_MOVE:
               scrollTo((int) -event.getX(), 0);
                //Log.d(TAG, "x is " + (int) event.getX() + " y is " + (int) event.getRawX());
                break;
            case MotionEvent.ACTION_UP:
                int distance = (int) Math.abs(event.getX() - mLastX);
                if (distance > getWidth() / 3) {
                    mScroller.startScroll(getScrollX(), getScrollY(), event.getX() - mLastX < 0 ? getWidth() - getScrollX() : -getScrollX(), 0);
                    Log.d(TAG, "x is " + (int) event.getX() + " y is " + event.getY() + " dx is " + (getWidth() - (event.getX()-mLastX)) + " scroller des " + (event.getY() - mLastY > getWidth() / 3));
                }else {
                    mScroller.startScroll(getScrollX(), getScrollY(), 0, 0);
                    Log.d(TAG, "x is " + (int) event.getX() + " y is " + event.getY() + " dx is " + (mLastX - event.getX()) + " scroller des " + (event.getY() - mLastY > getWidth() / 3));
                }
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        //Log.d(TAG, "computScroll is " + mScroller.computeScrollOffset());
        if (mScroller.computeScrollOffset()) {
            //Log.d(TAG, "computScroll is " + mScroller.getCurrX());
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    public int getFirstChildWidth(){
        return getChildCount() > 0 ? getChildAt(0).getWidth(): 0;
    }

    public int getFirstChildHeight(){
        return getChildCount() > 0 ? getChildAt(0).getHeight(): 0;
    }
}
