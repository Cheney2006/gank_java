package com.cheney.gankjava.ui.my;

import android.os.Bundle;

import com.cheney.gankjava.R;
import com.cheney.gankjava.base.DaggerPreferenceFragment;

public class SettingsFragment extends DaggerPreferenceFragment {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

}