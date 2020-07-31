package com.cheney.gankjava.ui;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.cheney.gankjava.R;
import com.cheney.gankjava.databinding.ActivityMainBinding;
import com.cheney.gankjava.dialog.ProgressFragment;
import com.cheney.gankjava.util.StatusBarUtil;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory factory;

    private SessionViewModel sessionViewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBar(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        sessionViewModel = new ViewModelProvider(this, factory).get(SessionViewModel.class);
//        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.navView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.homeFragment:
                case R.id.categoryFragment:
                case R.id.girlFragment:
                case R.id.myFragment:
                    binding.navView.setVisibility(View.VISIBLE);
                    break;
                default:
                    binding.navView.setVisibility(View.GONE);
                    break;
            }
        });

        navController.navigate(R.id.action_to_splashFragment);

        initProgress();
    }

    private void initProgress() {
        sessionViewModel.progressLiveData.observe(this, progressBean -> {
            ProgressFragment progressFragment = (ProgressFragment) getSupportFragmentManager().findFragmentByTag(ProgressFragment.TAG);
            if (progressFragment == null) {
                progressFragment = new ProgressFragment(progressBean.getMessage(), progressBean.isCancelable());
            }
            if (progressBean.isFinished() && progressFragment.isAdded()) {
                progressFragment.dismissAllowingStateLoss();
            } else {
                //如果已经显示过，则先隐藏
                if (progressFragment.isAdded()) {
                    progressFragment.dismissAllowingStateLoss();
                }
                progressFragment.show(getSupportFragmentManager(), ProgressFragment.TAG);
            }
        });
    }

}