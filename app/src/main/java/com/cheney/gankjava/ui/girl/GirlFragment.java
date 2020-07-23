package com.cheney.gankjava.ui.girl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;

import com.cheney.gankjava.R;
import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.databinding.FragmentGirlBinding;
import com.cheney.gankjava.util.StatusBarUtil;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class GirlFragment extends DaggerFragment {


    @Inject
    ViewModelProvider.Factory factory;

    private GirlViewModel viewModel;

    private FragmentGirlBinding binding;

    private GirlAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, factory).get(GirlViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_girl, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StatusBarUtil.setToolbarWithStatusBar(requireContext(),binding.toolbarLayout.toolbar);
        binding.toolbarLayout.toolbar.setTitle(R.string.title_girl);

        adapter = new GirlAdapter();
        binding.recyclerView.setAdapter(adapter);
        viewModel.pagedListLiveData.observe(getViewLifecycleOwner(), ganks -> adapter.submitList(ganks));
    }
}