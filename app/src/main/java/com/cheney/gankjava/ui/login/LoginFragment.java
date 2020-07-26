package com.cheney.gankjava.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.cheney.gankjava.R;
import com.cheney.gankjava.bean.Progress;
import com.cheney.gankjava.bean.User;
import com.cheney.gankjava.databinding.FragmentLoginBinding;
import com.cheney.gankjava.dialog.ProgressFragment;
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
        sessionViewModel =new ViewModelProvider(requireActivity(),factory).get(SessionViewModel.class);
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
        StatusBarUtil.setToolbarWithStatusBar(requireContext(),binding.toolbarLayout.toolbar);

        final EditText usernameEditText = view.findViewById(R.id.username);
        final EditText passwordEditText = view.findViewById(R.id.password);
        final Button loginButton = view.findViewById(R.id.login);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginViewModel.progressMutableLiveData.observe(getViewLifecycleOwner(), new Observer<Progress>() {
            @Override
            public void onChanged(Progress progress) {
                ProgressFragment progressFragment= null;
                if(progress.isFinished()&&progressFragment!=null){
                    progressFragment.dismissAllowingStateLoss();
                }else{
                    if(progressFragment==null){
                        progressFragment= new ProgressFragment();
                        progressFragment.showNow(getParentFragmentManager(),"progress");
                    }
                }
            }
        });
        loginViewModel.userInfo.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                sessionViewModel.userInfo.postValue(user);
            }
        });
        loginViewModel.error.observe(getViewLifecycleOwner(), new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
//                Navigation.findNavController();
                NavHostFragment.findNavController(getParentFragment()).navigateUp();
            }
        });
    }

}