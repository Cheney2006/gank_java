package com.cheney.gankjava.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.cheney.gankjava.R;
import com.cheney.gankjava.bean.GankBanner;
import com.cheney.gankjava.databinding.FragmentHomeBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

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

        binding.appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset >= 0) {
                binding.swipeRefresh.setEnabled(true);
            } else {
                binding.swipeRefresh.setEnabled(false);
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        binding.toolbar.myToolbar.setTitle(R.string.title_home);

        HomeAdapter adapter = new HomeAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        homeViewModel.banners.observe(getViewLifecycleOwner(), new Observer<List<GankBanner>>() {
            @Override
            public void onChanged(List<GankBanner> gankBanners) {
                binding.banner.addBannerLifecycleObserver(getViewLifecycleOwner())//添加生命周期观察者
                        .setAdapter(new HomeBannerAdapter(gankBanners))
                        .setIndicator(new CircleIndicator(requireContext()))
                        .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                        .start();
            }
        });
        homeViewModel.ganks.observe(getViewLifecycleOwner(), ganks -> {
            adapter.submitList(ganks);
        });
    }
}