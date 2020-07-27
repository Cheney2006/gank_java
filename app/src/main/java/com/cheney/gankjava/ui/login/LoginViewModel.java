package com.cheney.gankjava.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cheney.gankjava.bean.User;
import com.cheney.gankjava.repository.GitHubRepository;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

    private final GitHubRepository repository;

   public MutableLiveData<Throwable> error=new MutableLiveData<>();

    public MutableLiveData<String> username=new MutableLiveData<>();
    public MutableLiveData<String> password=new MutableLiveData<>();

   public MutableLiveData<User> userInfo=new MutableLiveData<User>();

    public MutableLiveData<Boolean> isLoading=new MutableLiveData();

    @Inject
    public LoginViewModel(GitHubRepository repository) {
        this.repository = repository;
    }

    public void login() {
        isLoading.postValue(true);
        repository.getAccessToken(username.getValue(),password.getValue()).subscribe(response -> {
            isLoading.postValue(false);
            System.out.println(response.toString());
            //测试使用
            User user=new User();
            user.setUsername(username.getValue());
            user.setPassword(password.getValue());
            userInfo.postValue(user);
        }, throwable -> {
            isLoading.postValue(false);
            //测试使用
            User user=new User();
            user.setUsername(username.getValue());
            user.setPassword(password.getValue());
            userInfo.postValue(user);

            error.postValue(throwable);
        });
    }



}