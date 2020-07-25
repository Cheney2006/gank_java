package com.cheney.gankjava.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cheney.gankjava.repository.GitHubRepository;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

    private final GitHubRepository repository;

    MutableLiveData<Throwable> error=new MutableLiveData<>();

    @Inject
    public LoginViewModel(GitHubRepository repository) {
        this.repository = repository;
    }

    public void login() {
        repository.getAccessToken("","").subscribe(response -> {
            System.out.println(response.toString());
        }, throwable -> error.postValue(throwable));
    }

}