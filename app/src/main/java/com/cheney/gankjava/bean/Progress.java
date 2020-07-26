package com.cheney.gankjava.bean;

public class Progress {


    private Status status;

    private String message;

    private boolean cancelable;

    public enum Status{
        LOADING,FINISHED
    }

    public boolean isFinished(){
        return status!=null&&Status.FINISHED==status;
    }


    public void setLoading(String message,boolean cancelable){
        status=Status.LOADING;
        this.message=message;
        this.cancelable=cancelable;
    }


    public void setFinished(){
        status=Status.FINISHED;
    }
}
