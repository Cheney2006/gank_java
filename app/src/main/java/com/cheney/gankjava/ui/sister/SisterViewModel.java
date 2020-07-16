package com.cheney.gankjava.ui.sister;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class SisterViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    @Inject
    public SisterViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}