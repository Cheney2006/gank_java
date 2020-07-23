package com.cheney.gankjava.ui.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cheney.gankjava.R;

import dagger.android.support.DaggerAppCompatActivity;

public class WebViewActivity extends DaggerAppCompatActivity {

    public static final String KEY_TITLE = "title";
    public static final String KEY_URL = "url";

    public static void startWebView(Context context, String title, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(KEY_TITLE, title);
        intent.putExtra(KEY_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().setFragmentFactory(new WebViewFragmentFactory(getIntent().getStringExtra(KEY_TITLE), getIntent().getStringExtra(KEY_URL)));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //动态添加
//        getSupportFragmentManager().beginTransaction().add(R.id.content,WebViewFragment.class,getIntent().getExtras()).commit();

    }


}