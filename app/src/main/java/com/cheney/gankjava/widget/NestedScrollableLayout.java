package com.cheney.gankjava.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheney.gankjava.R;

/**
 * ViewPager2滑动事件冲突
 * 1、SwipeRefreshLayout嵌套RecyclerView嵌套ViewPager2
 * <pre>
 *      <FixDragLayout android:orientation="horizontal">
 *           <ViewPager2/>
 *      </FixDragLayout>
 * </pre>
 * 2. ViewPager2嵌套SwipeRefreshLayout和RecyclerView
 * <pre>
 *      <FixDragLayout android:orientation="verticle">
 *          <SwipleRefreshLayout>
 *               <RecyclerView />
 *          </SwipleRefreshLayout>
 *      </FixDragLayout>
 * </pre>
 */
public class NestedScrollableLayout extends FrameLayout {

    private int touchSlop = 0;
    private float downX = 0f;
    private float downY = 0f;
    private boolean isDragged;

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;
    private int orientation = VERTICAL;

    public NestedScrollableLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public NestedScrollableLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        getAttrs(context, attrs);
    }

    public NestedScrollableLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        getAttrs(context, attrs);
    }


    public NestedScrollableLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        getAttrs(context, attrs);
    }

    private void init(Context context) {
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        //取属性
    }

    private void getAttrs(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NestedScrollableLayout);
        orientation = a.getInt(R.styleable.NestedScrollableLayout_android_orientation, HORIZONTAL);
        a.recycle();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                isDragged = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isDragged) {
                    float dx = Math.abs(ev.getX() - downX);
                    float dy = Math.abs(ev.getY() - downY);
                    if (orientation == HORIZONTAL) {
                        isDragged = dx > touchSlop && dx > dy;
                    } else if (orientation == VERTICAL) {
                        isDragged = dy > touchSlop && dy > dx;
                    }
                }
                getParent().requestDisallowInterceptTouchEvent(isDragged);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isDragged = false;
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
