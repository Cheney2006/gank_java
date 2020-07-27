package com.cheney.gankjava.ui.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.cheney.gankjava.R;
import com.cheney.gankjava.bean.User;
import com.cheney.gankjava.constants.Constants;
import com.cheney.gankjava.databinding.FragmentMyBinding;
import com.cheney.gankjava.ui.SessionViewModel;
import com.cheney.gankjava.util.StatusBarUtil;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;


public class MyFragment extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory factory;

    @Inject
    @Named(Constants.NamedKey.VERSION_NAME)
    String versionName;

    private SessionViewModel sessionViewModel;

    private MyViewModel viewModel;

    private FragmentMyBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sessionViewModel = new ViewModelProvider(requireActivity(), factory).get(SessionViewModel.class);
        viewModel = new ViewModelProvider(this, factory).get(MyViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.setVersionName(versionName);
        binding.setEventHandler(new EventHandlers());

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StatusBarUtil.setToolbarWithStatusBar(requireContext(), binding.toolbarLayout.toolbar);
        binding.toolbarLayout.toolbar.setTitle(R.string.title_my);

//        sessionViewModel.userInfo.observe(getViewLifecycleOwner(), user -> {
//            binding.usernameTv.setText(user.getUsername());
//        });

        //通过Fragment setFragmentResultListener传递参数
        getParentFragmentManager().setFragmentResultListener("userInfo", this, (requestKey, result) -> {
            String username=result.getString("username");
            if (username != null) {
                binding.usernameTv.setText(username);
            }
        });
    }

    public class EventHandlers{
        public void login(View view) {
            Navigation.findNavController(view).navigate(R.id.action_myFragment_to_loginFragment);
        }
    }
}