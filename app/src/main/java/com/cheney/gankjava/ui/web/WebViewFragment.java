package com.cheney.gankjava.ui.web;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.cheney.gankjava.R;
import com.cheney.gankjava.databinding.FragmentWebviewBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class WebViewFragment extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private WebViewViewModel mViewModel;

    private FragmentWebviewBinding binding;

    private String title;
    private String url;

    public WebViewFragment(String title, String url) {
        this.title=title;
        this.url = url;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this, viewModelFactory).get(WebViewViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(mViewModel);

        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //设置进度
                mViewModel.progress.postValue(newProgress);
            }
        });
        binding.webView.getSettings().setJavaScriptEnabled(true);


        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.toolbarLayout.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24);
        binding.toolbarLayout.toolbar.setNavigationOnClickListener(v -> requireActivity().finish());
//        String title = WebViewFragmentArgs.fromBundle(getArguments()).getTitle();
        binding.toolbarLayout.toolbar.setTitle(title);
//        String url = WebViewFragmentArgs.fromBundle(getArguments()).getUrl();
        if (!TextUtils.isEmpty(url)) {
            binding.webView.loadUrl(url);
        }
    }


}