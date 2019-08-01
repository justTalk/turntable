package com.viva.live.now.up.net.support;

import android.app.Activity;
import android.util.SparseArray;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.subscribers.ResourceSubscriber;


/**
 * 对Subscriber的管理类，帮助实现在Activity的特定生命周期的自动解绑与之绑定的所有Subscriber
 *
 * 【不会持有activity的实例，只用一个特征值(hashcode)来作为保存的key，所以不过造成内存泄漏的问题】
 */

public class SubscribersManager {
    private static final String TAG = "SubscribersManager";
    private static volatile SubscribersManager defaultInstance;
    private final SparseArray<CopyOnWriteArrayList<ResourceSubscriber>> observersByActivity = new SparseArray<>();

    /**
     * 获取SubscribersManager的单例实现
     * @return
     */
    public static SubscribersManager getDefault() {
        if (defaultInstance == null) {
            synchronized (SubscribersManager.class) {
                if (defaultInstance == null) {
                    defaultInstance = new SubscribersManager();
                }
            }
        }
        return defaultInstance;
    }

    /**
     * 把一个subscriber跟activity绑定
     * @param activity
     * @param subscriber
     */
    public synchronized void  addObserver(Activity activity, ResourceSubscriber subscriber) {
        CopyOnWriteArrayList<ResourceSubscriber> observers = observersByActivity.get(getCode(activity));
        if (observers == null) {
            observers = new CopyOnWriteArrayList<>();
            observersByActivity.put(getCode(activity), observers);
        }
        observers.add(subscriber);
    }


    private List<ResourceSubscriber> getObserversByActivity(Activity activity) {
        CopyOnWriteArrayList<ResourceSubscriber> observers = observersByActivity.get(getCode(activity));
        if (observers == null) {
            return new CopyOnWriteArrayList<>();
        }
        return observers;
    }

    /**
     * 取消与某个activity绑定的所有subscriber的订阅（cancel网络请求），并从当前SubscribersManager中清除
     * @param activity
     */
    public synchronized void clearByActivity(Activity activity) {
        List<ResourceSubscriber> subscriberList = SubscribersManager.getDefault().getObserversByActivity(activity);
        for (ResourceSubscriber subscriber : subscriberList) {
            if (!subscriber.isDisposed()) {
                subscriber.dispose();
            }
        }
        observersByActivity.remove(getCode(activity));
    }

    private static Integer getCode(Activity activity) {
        return activity.hashCode();
    }
}
