package com.cheney.gankjava.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.cheney.gankjava.R;
import com.cheney.gankjava.bean.ProgressBean;
import com.cheney.gankjava.bean.User;
import com.cheney.gankjava.databinding.FragmentLoginBinding;
import com.cheney.gankjava.ui.SessionViewModel;
import com.cheney.gankjava.util.StatusBarUtil;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class LoginFragment extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory factory;

    private SessionViewModel sessionViewModel;

    private LoginViewModel loginViewModel;

    private FragmentLoginBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionViewModel = new ViewModelProvider(requireActivity(), factory).get(SessionViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);

        binding.toolbarLayout.toolbar.setTitle(R.string.title_login);
        StatusBarUtil.setToolbarWithStatusBar(requireContext(), binding.toolbarLayout.toolbar);

        loginViewModel.username.setValue("张三");

        loginViewModel.isLoading.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null && isLoading) {
                sessionViewModel.progressLiveData.postValue(ProgressBean.create(getString(R.string.dialog_login), false));
            } else {
                sessionViewModel.progressLiveData.postValue(ProgressBean.finished());
            }
        });

        loginViewModel.userInfo.observe(getViewLifecycleOwner(), user -> {
//            sessionViewModel.userInfo.postValue(user);
            Bundle result = new Bundle();
            result.putString("username", user.getUsername());
            getParentFragmentManager().setFragmentResult("userInfo", result);
        });
        loginViewModel.error.observe(getViewLifecycleOwner(), throwable -> NavHostFragment.findNavController(this).navigateUp());
    }

}