package com.cheney.gankjava.ui.home;

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
import com.cheney.gankjava.databinding.FragmentHomeBinding;

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
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.toolbar.myToolbar.setTitle(getString(R.string.title_home));

        HomeAdapter adapter = new HomeAdapter(getViewLifecycleOwner());
        binding.hotRv.setAdapter(adapter);
        binding.hotRv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        homeViewModel.hot.observe(getViewLifecycleOwner(), homeItems -> {
            adapter.submitList(homeItems);
        });
    }
}