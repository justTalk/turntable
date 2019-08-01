package com.viva.live.now.up.net;

import android.app.Activity;
import android.util.Log;

import com.viva.live.now.up.net.base.BaseDataWrapper;

import java.net.UnknownHostException;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * CallProxy层的基类，封装了一个Builder，用以构建一个请求
 *
 */

public class BaseCallProxy {

    /**
     * 登录过期
     */
    public static final int ERROR_CODE_ACCESS_TOKEN_INVALID = 300;
    /**
     * 登录受限（用户被封啦）
     */
    public static final int ERROR_CODE_LOGIN_NOT_PERMIT = 1058;
    /**
     * <p>用以构建一个请求，所需要的组件有：
     * <p>
     * <li>flowable</li> 数据源
     * <li>callback</li> 基础回调
     * <li>callbackExtra</li>【可选】额外回调
     * <li>callbackIO</li>【可选】运行在io线程的回调
     * <li>Activity</li> 【可选】要绑定的Activity
     * 等
     *
     * @param <T>
     */
    public static class Builder<T> {

        Activity mActivity;
        Flowable<BaseDataWrapper<T>> mFlowable;
        RetrofitCallback<T> mCallback;
        RetrofitCallback<T> mCallbackExtra;
        RetrofitCallback<T> mCallbackIO;

        /**
         * 设置当前的情况跟一个activity绑定，绑定之后，当activity的destroy的时候，当前请求会被cancel
         * <p>
         * 注意：要使这一机制生效，需要是app的Application继承SubscribersManagerApplication；
         * 或者在Application中设置一个onActivityDestroyed的回调，并在其中调用SubscribersManager.getDefault().clearByActivity(activity)
         *
         * @param activity 要绑定的activity
         * @return this
         * @see {@link SubscribersManagerApplication}
         */
        public Builder<T> bindToActivity(final Activity activity) {
            mActivity = activity;
            return this;
        }

        private Builder<T> setFlowable(final Flowable<BaseDataWrapper<T>> flowable) {
            mFlowable = flowable;
            return this;
        }

        private Builder<T> setCallback(final RetrofitCallback<T> callback) {
            mCallback = callback;
            return this;
        }

        /**
         * 设置一个额外的callbackExtra，callbackExtra会在callback之前执行。
         * 建议这里可以放一些在需要在业务代码执行前的做的操作，比如缓存等
         * callbackExtra会在主线程运行
         *
         * @param callbackExtra
         * @return this
         */
        public Builder<T> setCallbackExtra(final RetrofitCallback<T> callbackExtra) {
            mCallbackExtra = callbackExtra;
            return this;
        }

        /**
         * 设置一个callbackIO，不同于callback或callbackExtra，callbackIO会在io线程执行。
         *
         * @param callbackIO
         * @return this
         */
        public Builder<T> setCallbackIO(final RetrofitCallback<T> callbackIO) {
            mCallbackIO = callbackIO;
            return this;
        }

        /**
         * 执行订阅，这个动作会触发flowable中的动作开始执行。
         */
        public void doSubscribe() {
            if (mFlowable == null) {
                throw new RuntimeException("flowable can not be NULL !");
            }
            BaseCallProxy.doSubscribe(mActivity, mFlowable, mCallback, mCallbackExtra, mCallbackIO);
        }

        /**
         * 创建一个基础的Builder
         *
         * @param flowable 数据源
         * @param callback 基础的回调
         * @param <T>      业务所需的数据实体类型
         * @return this
         */
        public static <T> Builder<T> newInstance(Flowable<BaseDataWrapper<T>> flowable,
                                                 RetrofitCallback<T> callback) {
            return new Builder<T>().setFlowable(flowable).setCallback(callback);
        }

    }

    private static <T> void doSubscribe(Activity activity,
                                        Flowable<BaseDataWrapper<T>> observable,
                                        final RetrofitCallback<T> callback,
                                        final RetrofitCallback<T> callbackExtra,
                                        final RetrofitCallback<T> callbackIO) {
        MySubscriber<T> subscriber = new MySubscriber<T>() {

            @Override
            public void onError(final int errorCode, final String errorMessage) {
                if (BuildConfig.DEBUG) {
                    Log.e("HttpApi", "response is onError [errorCode  " + errorCode + "  errorMessage  " + errorMessage + "]");
                }
                if (callbackIO != null) {
                    Flowable.just(true)
                            .observeOn(Schedulers.io())
                            .subscribe(new Consumer<Object>() {
                                @Override
                                public void accept(final Object o) throws Exception {
                                    callbackIO.onError(errorCode, errorMessage);
                                }
                            });
                }
                if (callbackExtra != null) {
                    callbackExtra.onError(errorCode, errorMessage);
                }
                callback.onError(errorCode, errorMessage);
                callback.onFinish();

                // 需要重新登录的 ErrorCode
                if (errorCode == ERROR_CODE_ACCESS_TOKEN_INVALID) {
                }
            }

            @Override
            public void onSuccess(final T result) {
                if (BuildConfig.DEBUG) {
                    Log.e("HttpApi", "response is onSuccess [" + result.toString()+ "]");
                }
                if (callbackIO != null) {
                    Flowable.just(true)
                            .observeOn(Schedulers.io())
                            .subscribe(new Consumer<Object>() {
                                @Override
                                public void accept(final Object o) throws Exception {
                                    callbackIO.onSuccess(result);
                                    callbackIO.onFinish();
                                }
                            });
                }
                if (callbackExtra != null) {
                    callbackExtra.onSuccess(result);
                    callbackExtra.onFinish();
                }
                callback.onSuccess(result);
                callback.onFinish();
            }

            @Override
            public void onException(final Throwable e) {
                e.printStackTrace();
                if (BuildConfig.DEBUG) {
                    Log.e("HttpApi", "response is onException [message  " + e.getMessage() + "]");
                }
                if (e instanceof UnknownHostException) {
                    if (callbackIO != null) {
                        Flowable.just(true)
                                .observeOn(Schedulers.io())
                                .subscribe(new Consumer<Object>() {
                                    @Override
                                    public void accept(final Object o) throws Exception {
                                        callbackIO.onNoNetWork();
                                    }
                                });
                    }
                    if (callbackExtra != null) {
                        callbackExtra.onNoNetWork();
                    }
                    callback.onNoNetWork();
                } else {
                    if (callbackIO != null) {
                        Flowable.just(true)
                                .observeOn(Schedulers.io())
                                .subscribe(new Consumer<Object>() {
                                    @Override
                                    public void accept(final Object o) throws Exception {
                                        callbackIO.onException(e);
                                    }
                                });
                    }
                    if (callbackExtra != null) {
                        callbackExtra.onException(e);
                    }
                    callback.onException(e);
                }
                if (callbackExtra != null) {
                    callbackExtra.onFinish();
                }
                callback.onFinish();
            }
        };
        observable.observeOn(AndroidSchedulers.mainThread())
                .lift(new OperatorBindActivity<BaseDataWrapper<T>>(activity))
                .subscribe(subscriber);

    }

}
