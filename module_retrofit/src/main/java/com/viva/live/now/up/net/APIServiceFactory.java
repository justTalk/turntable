package com.viva.live.now.up.net;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂类，管理并提供Service接口的具体实现（单例模式）
 * Created by Liu On 2019/4/13
 * Description:
 * email: mingming.liu@quvideo.com
 */

public class APIServiceFactory {

    private static Map<Class<?>, Object> mServiceInstanceMap = new HashMap<>();

    /**
     * @param cls
     * @param <T>
     * @return
     */
    @SuppressWarnings({"unchecked"})
    public static synchronized <T> T getServiceInstance(Class<T> cls) {
        if (mServiceInstanceMap.get(cls) == null) {
            mServiceInstanceMap.put(cls, QYXXRetrofitClient.getRetrofit().create(cls));
        }
        return (T) mServiceInstanceMap.get(cls);
    }
}