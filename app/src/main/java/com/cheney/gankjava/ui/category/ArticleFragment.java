package com.cheney.gankjava.ui.category;

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
import com.cheney.gankjava.databinding.FragmentArticleBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class ArticleFragment extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory factory;

    private ArticleViewModel mViewModel;

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
        mViewModel = new ViewModelProvider(this, factory).get(ArticleViewModel.class);
        mViewModel.initType(categoryType);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ArticleAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mViewModel.ganks.observe(getViewLifecycleOwner(), ganks -> adapter.submitList(ganks));
    }

}