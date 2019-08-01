package com.viva.live.now.up.net;


import com.viva.live.now.up.net.base.BaseDataWrapper;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 自定义Subscriber，把Subscriber的回调方法重新排列，以更适应业务需求
 * 因为是基于BaseDataWrapper和BaseData的，所以当前类需要根据实际业务进行调整
 */

public abstract class MySubscriber<T> extends ResourceSubscriber<BaseDataWrapper<T>> {

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public final void onComplete() {
        if (!isDisposed()) {
            dispose();
        }
    }

    @Override
    public final void onError(Throwable e) {
        onException(e);
        if (!isDisposed()) {
            dispose();
        }
    }

    @Override
    public final void onNext(BaseDataWrapper<T> t) {
        if (t.isResult()) {
            onSuccess(t.getData());
        } else {
            onError(t.getStateCode(), t.getMessage());
        }
    }

    public abstract void onError(int errorCode, String errorMessage);

    public abstract void onSuccess(T t);

    public abstract void onException(Throwable e);


}
