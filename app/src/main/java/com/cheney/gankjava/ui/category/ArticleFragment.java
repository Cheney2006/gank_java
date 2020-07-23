package com.cheney.gankjava.ui.category;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.cheney.gankjava.R;
import com.cheney.gankjava.bean.CategoryType;
import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.databinding.FragmentArticleBinding;
import com.cheney.gankjava.ui.home.GankItemClickCallback;
import com.cheney.gankjava.ui.web.WebViewActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class ArticleFragment extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory factory;

    private ArticleViewModel viewModel;

    private FragmentArticleBinding binding;

    private CategoryType categoryType;

    private ArticleAdapter adapter;

    public ArticleFragment(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public static ArticleFragment newInstance(CategoryType categoryType) {
        return new ArticleFragment(categoryType);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, factory).get(ArticleViewModel.class);
        viewModel.initType(categoryType);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ArticleAdapter((view, gank) -> WebViewActivity.startWebView(requireContext(), gank.getTitle(), gank.getUrl()));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        viewModel.pagedListLiveData.observe(getViewLifecycleOwner(), ganks -> adapter.submitList(ganks));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ArticleFragment onCreate categoryType ="+categoryType.getTitle());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("ArticleFragment onAttach categoryType ="+categoryType.getTitle());
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("ArticleFragment onResume categoryType ="+categoryType.getTitle());
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("ArticleFragment onPause categoryType ="+categoryType.getTitle());
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("ArticleFragment onStop categoryType ="+categoryType.getTitle());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("ArticleFragment onDestroyView categoryType ="+categoryType.getTitle());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("ArticleFragment onDetach categoryType ="+categoryType.getTitle());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("ArticleFragment onDestroy categoryType ="+categoryType.getTitle());
    }
}