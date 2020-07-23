package com.cheney.gankjava.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.cheney.gankjava.R;
import com.cheney.gankjava.util.StatusBarUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBar(this);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            System.out.println("destination.getId()="+destination.getLabel());
            //单页面，隐藏底部
            switch (destination.getId()) {
                case R.id.navigation_home:
                case R.id.navigation_category:
                case R.id.navigation_girl:
                case R.id.navigation_my:
                    navView.setVisibility(View.VISIBLE);
                    break;
                default:
                    navView.setVisibility(View.GONE);
            }
        });
    }

}