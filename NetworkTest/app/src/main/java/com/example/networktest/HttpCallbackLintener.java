package com.example.networktest;

public interface HttpCallbackLintener {
    public abstract void onFinish(String response);
    public abstract void onError(Exception e);
}
