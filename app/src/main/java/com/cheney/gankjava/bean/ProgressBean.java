package com.cheney.gankjava.bean;

public class ProgressBean {


    private Status status;

    private String message;

    private boolean cancelable;

    public ProgressBean(Status status) {
        this.status = status;
    }

    public ProgressBean(Status status, String message, boolean cancelable) {
        this.status = status;
        this.message = message;
        this.cancelable = cancelable;
    }

    public enum Status {
        LOADING, FINISHED
    }

    public String getMessage() {
        return message;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public boolean isFinished() {
        return status != null && Status.FINISHED == status;
    }

    public static ProgressBean create(String message, boolean cancelable) {
        return new ProgressBean(Status.LOADING, message, cancelable);
    }

    public static ProgressBean finished() {
        return new ProgressBean(Status.FINISHED);
    }

}
