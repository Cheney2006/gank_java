package com.cheney.gankjava.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.HashMap;

public class LifecycleCallbacks {
    private static final String TAG = "LifecycleCallbacks";

    private HashMap<String, FragmentManager.FragmentLifecycleCallbacks> lifecycleCallbacksHashMap = null;

    private LifecycleCallbacks() {
        lifecycleCallbacksHashMap = new HashMap<>();
    }

    /**
     * 静态内部类单例
     */
    private static class SingletonHolder {
        private static final LifecycleCallbacks INSTANCE = new LifecycleCallbacks();
    }

    public static final LifecycleCallbacks getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void reigister(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                Log.d(TAG, activity.getClass().getSimpleName() + "------>onActivityCreated");
                if (activity instanceof FragmentActivity) {
                    FragmentManager.FragmentLifecycleCallbacks lifecycleCallbacks = new MyFragmentLifecycleCallbacks();
                    lifecycleCallbacksHashMap.put(activity.getClass().getSimpleName(), lifecycleCallbacks);
                    ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(lifecycleCallbacks, true);
                }
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                Log.d(TAG, activity.getClass().getSimpleName() + "------>onActivityStarted");
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                Log.d(TAG, activity.getClass().getSimpleName() + "------>onActivityResumed");
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                Log.d(TAG, activity.getClass().getSimpleName() + "------>onActivityPaused");
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                Log.d(TAG, activity.getClass().getSimpleName() + "------>onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                Log.d(TAG, activity.getClass().getSimpleName() + "------>onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                Log.d(TAG, activity.getClass().getSimpleName() + "------>onActivityDestroyed");
                if (activity instanceof FragmentActivity) {
                    ((FragmentActivity) activity).getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(lifecycleCallbacksHashMap.get(activity.getClass().getSimpleName()));
                }
            }
        });
    }

    private static class MyFragmentLifecycleCallbacks extends FragmentManager.FragmentLifecycleCallbacks {
        @Override
        public void onFragmentAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
            super.onFragmentAttached(fm, f, context);
            Log.d(TAG, f.getClass().getSimpleName()+" tag " + f.getTag() + "------>onActivityCreated");
        }

        @Override
        public void onFragmentCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
            super.onFragmentCreated(fm, f, savedInstanceState);
            Log.d(TAG, f.getClass().getSimpleName() + "------>onFragmentCreated");
        }

        @Override
        public void onFragmentViewCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull View v, @Nullable Bundle savedInstanceState) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState);
            Log.d(TAG, f.getClass().getSimpleName() + "------>onFragmentViewCreated");
        }

        @Override
        public void onFragmentStarted(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentStarted(fm, f);
            Log.d(TAG, f.getClass().getSimpleName() + "------>onFragmentStarted");
        }

        @Override
        public void onFragmentResumed(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentResumed(fm, f);
            Log.d(TAG, f.getClass().getSimpleName() + "------>onFragmentResumed");
        }

        @Override
        public void onFragmentPaused(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentPaused(fm, f);
            Log.d(TAG, f.getClass().getSimpleName() + "------>onFragmentPaused");
        }

        @Override
        public void onFragmentStopped(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentStopped(fm, f);
            Log.d(TAG, f.getClass().getSimpleName() + "------>onFragmentStopped");
        }

        @Override
        public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentViewDestroyed(fm, f);
            Log.d(TAG, f.getClass().getSimpleName() + "------>onFragmentViewDestroyed");
        }

        @Override
        public void onFragmentDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentDestroyed(fm, f);
            Log.d(TAG, f.getClass().getSimpleName() + "------>onFragmentDestroyed");
        }

        @Override
        public void onFragmentDetached(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentDetached(fm, f);
            Log.d(TAG, f.getClass().getSimpleName() + "------>onFragmentDetached");
        }
    }
}
