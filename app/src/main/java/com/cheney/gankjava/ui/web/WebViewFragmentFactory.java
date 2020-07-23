package com.cheney.gankjava.ui.web;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.cheney.gankjava.ui.category.ArticleFragment;

public class WebViewFragmentFactory extends FragmentFactory {

    private String title;
    private String url;

    public WebViewFragmentFactory(String title, String url) {
        this.title = title;
        this.url = url;
    }

    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        System.out.println("className=" + className);
//        Class aClass= loadFragmentClass(classLoader, className);
////        aClass.newInstance();
        if (className.equals(WebViewFragment.class.getName())) {
            return new WebViewFragment(title, url);
        }
        return super.instantiate(classLoader, className);
    }
}
