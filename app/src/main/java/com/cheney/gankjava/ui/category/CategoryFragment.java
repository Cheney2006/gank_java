package com.cheney.gankjava.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.cheney.gankjava.R;
import com.cheney.gankjava.bean.CategoryType;
import com.cheney.gankjava.databinding.FragmentCategoryBinding;
import com.cheney.gankjava.util.StatusBarUtil;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class CategoryFragment extends DaggerFragment {


    public static final String TAG = "CategoryFragment";

    @Inject
    ViewModelProvider.Factory factory;

    private CategoryViewModel categoryViewModel;

    private FragmentCategoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel = new ViewModelProvider(this, factory).get(CategoryViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(categoryViewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StatusBarUtil.setToolbarWithStatusBar(requireContext(), binding.toolbarLayout.toolbar);
        binding.toolbarLayout.toolbar.setTitle(R.string.title_category);
        binding.toolbarLayout.toolbar.setElevation(0);

        categoryViewModel.categoryTypes.observe(getViewLifecycleOwner(), categoryTypes -> {
            initPager(categoryTypes);
        });


    }

    private void initPager(List<CategoryType> categoryTypes) {
        //懒加载https://blog.csdn.net/qq_36486247/article/details/103959356
//        binding.pager.setOffscreenPageLimit(categoryTypes.size());
        binding.pager.setAdapter(new CategoryPagerAdapter(this, categoryTypes));
        new TabLayoutMediator(binding.tabLayout, binding.pager, (tab, position) -> tab.setText(categoryTypes.get(position).getTitle())).attach();
    }
}