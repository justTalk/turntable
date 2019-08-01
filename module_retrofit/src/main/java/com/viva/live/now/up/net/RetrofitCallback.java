package com.viva.live.now.up.net;

/**
 * 当前框架下的回调封装
 */

public abstract class RetrofitCallback<T> {

    public void onError(int errorCode, String errorMessage) {}

    public abstract void onSuccess(T t);

    public void onException(Throwable e) {}

    public void onNoNetWork() {}

    public void onFinish(){}

}
