package com.viva.live.now.up.net;

import android.app.Activity;

import com.viva.live.now.up.net.support.SubscribersManager;

import org.reactivestreams.Subscriber;

import java.lang.ref.WeakReference;

import io.reactivex.FlowableOperator;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 *
 */

public class OperatorBindActivity<T> implements FlowableOperator<T, T> {

    final WeakReference<Activity> mActivity;

    public OperatorBindActivity(Activity activity) {
        mActivity = new WeakReference<>(activity);
    }

    @Override
    public Subscriber<? super T> apply(final Subscriber<? super T> observer) throws Exception {
        return new ResourceSubscriber<T>() {

            @Override
            protected void onStart() {
                super.onStart();
                if(mActivity.get() != null) {
                    SubscribersManager.getDefault().addObserver(mActivity.get(), this);
                }
            }

            @Override
            public void onNext(final T t) {
                observer.onNext(t);
            }

            @Override
            public void onError(final Throwable t) {
                observer.onError(t);
            }

            @Override
            public void onComplete() {
                observer.onComplete();
            }
        };
    }
}
