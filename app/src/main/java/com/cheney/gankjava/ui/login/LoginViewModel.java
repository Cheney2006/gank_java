package com.cheney.gankjava.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cheney.gankjava.bean.Progress;
import com.cheney.gankjava.bean.User;
import com.cheney.gankjava.repository.GitHubRepository;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

    private final GitHubRepository repository;

   public MutableLiveData<Throwable> error=new MutableLiveData<>();

    public MutableLiveData<String> username=new MutableLiveData<>();
    public MutableLiveData<String> password=new MutableLiveData<>();

   public MutableLiveData<User> userInfo=new MutableLiveData<User>();

    public MutableLiveData<Progress> progressMutableLiveData=new MutableLiveData<Progress>();

    @Inject
    public LoginViewModel(GitHubRepository repository) {
        this.repository = repository;
    }

    public void login() {
        Progress progress=new Progress();
        progress.setLoading("正在登录",false);
        progressMutableLiveData.postValue(progress);
        repository.getAccessToken(username.getValue(),password.getValue()).subscribe(response -> {
           progress.setFinished();
            progressMutableLiveData.postValue(progress);
            System.out.println(response.toString());
            //测试使用
            User user=new User();
            user.setUsername(username.getValue());
            user.setPassword(password.getValue());
            userInfo.postValue(user);

        }, throwable -> {
            progress.setFinished();
            progressMutableLiveData.postValue(progress);
            error.postValue(throwable);
        });
    }



}