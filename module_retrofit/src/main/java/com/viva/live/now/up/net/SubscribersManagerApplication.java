package com.viva.live.now.up.net;

import android.app.Activity;
import android.app.Application;

import com.viva.live.now.up.net.support.ActivityLifecycleCallbacksImp;
import com.viva.live.now.up.net.support.SubscribersManager;


/**
 * 自带SubscribersManager的Application
 * <p> 对于其他的Application来说，只需要继承SubscribersManagerApplication，
 * 就可以快速实现把网络请求跟Activity绑定的功能
 *
 */

public class SubscribersManagerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksImp() {
            @Override
            public void onActivityDestroyed(Activity activity) {
                // 在Activity销毁的时候，清除所有的与之关联的Subscribers
                SubscribersManager.getDefault().clearByActivity(activity);
            }
        });
    }
}
