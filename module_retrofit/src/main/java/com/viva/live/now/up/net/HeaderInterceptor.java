package com.viva.live.now.up.net;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * http请求头，原则上应该应用层注入的，目前先放在框架中
 * Created by Liu On 2019/4/13
 * Description:
 * email: mingming.liu@quvideo.com
 */

public class HeaderInterceptor implements Interceptor {

    public static final String HEADER_ACCEPT_LANGUAGE_KEY = "Accept-Lanuage";;
    private static final String HEADER_AUTHORIZATION_KEY = "Authorization";
    private static final String HEADER_ACCEPT_KEY = "Accept";
    private static final String HEADER_SIGN_KEY = "signkey";
    private static final String HEADER_CHANNEL_KEY = "channelid";
    private static final String HEADER_SUBCHANNEL_KEY = "subchannelid";

    String sAuthToken = "";// Bearer 当前用户登陆的token
    String sSignKey = "";
    String sLanguage = "zh_cn";
    String sAccept = ""; // "application/vnd.api.当前版本号+json";
    String sChannelId = "";
    String sSubChannelId = "";

    @Override
    public Response intercept(Chain chain) throws IOException {
        //统一设置请求头
        Request original = chain.request();
        Headers originHeader = original.headers();
        Request.Builder requestBuilder = original.newBuilder();
        requestBuilder.header(HEADER_ACCEPT_KEY, sAccept);
        if (originHeader == null || TextUtils.isEmpty(originHeader.get(HEADER_ACCEPT_LANGUAGE_KEY))) {
            requestBuilder.header(HEADER_ACCEPT_LANGUAGE_KEY, sLanguage);
        }
        requestBuilder.header(HEADER_AUTHORIZATION_KEY, sAuthToken);
        requestBuilder.header(HEADER_SIGN_KEY, sSignKey);
        requestBuilder.header(HEADER_CHANNEL_KEY, sChannelId);
        requestBuilder.header(HEADER_SUBCHANNEL_KEY, sSubChannelId);
        requestBuilder.method(original.method(), original.body());
        Request request = requestBuilder.build();
        Response proceed = chain.proceed(request);
        if (BuildConfig.DEBUG) {
            URL url = request.url().url();
            Log.e("HttpApi", "requestUrl is [" + new Gson().toJson(url) + "]");
            Log.e("HttpApi", "requestHeader is [" + request.headers().toString() + "]");
        }
        return proceed;
    }
}
