package com.cheney.gankjava.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.cheney.gankjava.R;

public class SplashFragment extends Fragment {

    public static final int DELAY_MILLIS = 3000;


    private Handler handler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handler.postDelayed(() -> {
//            Navigation.findNavController(requireActivity(), R.id.nav_mobile_fragment).navigateUp();
            NavHostFragment.findNavController(this).navigateUp();
//            NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_splashFragment);
        }, DELAY_MILLIS);
//        new CountDownTimer(3000,1000).seto.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

    }
}