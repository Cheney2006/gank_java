package com.cheney.gankjava.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.widget.Toolbar;

import com.cheney.gankjava.R;

public class StatusBarUtil {

    /**
     * 设置状态栏颜色为透明色，这样能保证状态栏为沉浸式状态
     * 根据SDK >= 21时，标题栏高度设为statusBarHeight(25dp)+titlBarHeight(48dp)
     * 若SDK < 21,标题栏高度直接为titlBarHeight
     */
    public static void setStatusBar(Activity activity) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            // Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住。
            window.getDecorView()
                    .setSystemUiVisibility(window.getDecorView().getSystemUiVisibility()
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = window.getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }


    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取toolbar高度
     *
     * @return
     */
    public static int getToolBarHeight(Context context) {
        int[] attrs = new int[]{R.attr.actionBarSize};
        TypedArray ta = context.obtainStyledAttributes(attrs);
        int toolBarHeight = ta.getDimensionPixelSize(0, -1);
        ta.recycle();
        return toolBarHeight;
    }


    public static void setToolbarWithStatusBar(Context context, Toolbar toolbar){
        int statusBarHeight= StatusBarUtil.getStatusBarHeight(context);
        int toolBarHeight= StatusBarUtil.getToolBarHeight(context);
        toolbar.getLayoutParams().height = statusBarHeight + toolBarHeight;
       toolbar.setPadding(0, statusBarHeight, 0, 0);
//       toolbar.setTitleMarginTop(statusBarHeight);
    }


    public static void enterFullScreen(Activity activity) {
        int flags = View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            flags |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE // 保持View Layout不变，隐藏状态栏或者导航栏后，View不会拉伸。
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // 让View全屏显示，Layout会被拉伸到StatusBar下面，不包含NavigationBar。
                     // | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION 让View全屏显示，Layout会被拉伸到StatusBar和NavigationBar下面。
                     ; //| View.SYSTEM_UI_FLAG_FULLSCREEN Activity全屏显示，且状态栏被隐藏覆盖掉。等同于（WindowManager.LayoutParams.FLAG_FULLSCREEN）。
                   //  | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION 隐藏虚拟按键(导航栏)。有些手机会用虚拟按键来代替物理按键。
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // 这个flag只有当设置了SYSTEM_UI_FLAG_HIDE_NAVIGATION才起作用。如果没有设置这个flag，
                // 任意的View相互动作都退出SYSTEM_UI_FLAG_HIDE_NAVIGATION模式。如果设置就不会退出。
                flags |= View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
        }

        View decorView = activity.getWindow().getDecorView();
        if (decorView != null) {
            decorView.setSystemUiVisibility(flags);
        }
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    public static void exitFullScreen(Activity activity) {
        int flags = View.SYSTEM_UI_FLAG_VISIBLE;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            flags |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE // 保持View Layout不变，隐藏状态栏或者导航栏后，View不会拉伸。
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // 让View全屏显示，Layout会被拉伸到StatusBar下面，不包含NavigationBar。
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;// 让View全屏显示，Layout会被拉伸到StatusBar和NavigationBar下面
        }

        View decorView = activity.getWindow().getDecorView();
        if (decorView != null) {
            decorView.setSystemUiVisibility(flags);
        }
    }
}
