package com.cheney.gankjava.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.cheney.gankjava.MainNavigationDirections;
import com.cheney.gankjava.R;
import com.cheney.gankjava.databinding.FragmentHomeBinding;
import com.cheney.gankjava.util.StatusBarUtil;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class HomeFragment extends DaggerFragment {

    public static final String TAG = "HomeFragment";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private HomeViewModel homeViewModel;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this, viewModelFactory).get(HomeViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(homeViewModel);

//        ((MainActivity) requireActivity()).setSupportActionBar(binding.toolbar.myToolbar);

        binding.appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
//            System.out.println("verticalOffset=" + verticalOffset + ",appBarLayout.getTotalScrollRange()=" + appBarLayout.getTotalScrollRange());
            if (verticalOffset >= 0) {
                //当滑动到顶部的时候开启
                binding.swipeRefresh.setEnabled(true);
            } else {
                //否则关闭
                binding.swipeRefresh.setEnabled(false);
            }

            if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                //或者toolbar不设置背景，或者背景设为透明,这里通过控制显示隐藏来处理
                binding.toolbarLayout.toolbar.setVisibility(View.VISIBLE);
                //CollapsingToolbarLayout 中的 title 覆盖了 Toolbar 中的 title
                binding.collapsingToolbarLayout.setTitle(getString(R.string.title_home));
//                binding.toolbar.myToolbar.setTitle(R.string.title_home);
            } else {
                binding.toolbarLayout.toolbar.setVisibility(View.GONE);
                binding.collapsingToolbarLayout.setTitle("");
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StatusBarUtil.setToolbarWithStatusBar(requireContext(), binding.toolbarLayout.toolbar);

        HomeAdapter adapter = new HomeAdapter((v, gank) -> {
            NavDirections navDirections = MainNavigationDirections.actionToWebViewFragment(gank.getTitle(), gank.getUrl());
//            NavHostFragment.findNavController(HomeFragment.this).navigate(navDirections);
            Navigation.findNavController(view).navigate(navDirections);
//            WebViewActivity.startWebView(requireContext(), gank.getTitle(), gank.getUrl());
        });
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        homeViewModel.banners.observe(getViewLifecycleOwner(), gankBanners -> binding.banner.addBannerLifecycleObserver(getViewLifecycleOwner())//添加生命周期观察者
                .setAdapter(new HomeBannerAdapter(gankBanners, (v, gankBanner) -> {
                    NavDirections navDirections = MainNavigationDirections.actionToWebViewFragment(gankBanner.getTitle(), gankBanner.getUrl());
//            NavHostFragment.findNavController(HomeFragment.this).navigate(navDirections);
                    Navigation.findNavController(view).navigate(navDirections);
                }))
                .setIndicator(new CircleIndicator(requireContext()))
                .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                .start());
        homeViewModel.ganks.observe(getViewLifecycleOwner(), ganks -> {
            adapter.submitList(ganks);
        });
    }


}