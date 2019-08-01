package com.viva.live.now.up.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Liu On 2019/4/13
 * Description:
 * email: mingming.liu@quvideo.com
 */

public class QYXXRetrofitClient {

    //https://api.hypstar.com/hotsoon/feed/?
    // type=video&tab_id=5&max_time=1564469593999&offset=10&count=10&req_from=feed_loadmore
    // &diff_stream=1&hb_info=H4sIAAAAAAAAAKtWSkyJz8xLy48vLcjJT0xRsoquVirISUxOzU3NK4kvqSxIVbJSMjQwMDQ0MTBU%0AqtXBLmtsYAGSja0FAKElsEpQAAAA%0A
    // &gaid=20bc6c35-e5f4-4634-a309-d1ae31c6f1b3
    // &ad_user_agent=Mozilla%2F5.0+%28Linux%3B+Android+8.1.0%3B+Nexus+5X+Build%2FOPM3.171019.016%3B+wv%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Version%2F4.0+Chrome%2F75.0.3770.143+Mobile+Safari%2F537.36
    // &feed_video_gap=0&live_sdk_version=700&iid=6719333975595321090&device_id=6719333452649399809&ac=wifi
    // &channel=google_play&aid=1145&app_name=live_i18n&version_code=700&version_name=7.0.0
    // &device_platform=android&ssmix=a&device_type=Nexus+5X&device_brand=google&language=zh
    // &os_api=27&os_version=8.1.0&openudid=ceef0c587dff6a96
    // &manifest_version_code=7006&resolution=1080*1794&dpi=420
    // &update_version_code=7006&_rticket=1564469722492&ab_version=864023%2C523365%2C889039%2C958601%2C1047276%2C860124%2C954877%2C845305%2C1019398%2C1045867%2C1047130%2C1045323%2C1046733
    // &client_version_code=700&sys_lang=zh_TW_%23Hant&tz_name=Asia%2FShanghai&tz_offset=28800&user_region=HK&locale=zh_TW_%23Hant&new_nav=0&ws_status=CONNECTED&ts=1564469722
    private static final String BASE_URL_DEBUG = "https://api.hypstar.com";
    private static final String BASE_URL_RELEASE = "https://api.hypstar.com";

    private static final int DEFAULT_TIMEOUT = 10;
    private static final int DEFAULT_RETROFIT = 0;

    private static boolean isReleaseVersion = false;
    private volatile static Retrofit retrofit;
    private static HeaderInterceptor sHeaderInterceptor = new HeaderInterceptor();

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (QYXXRetrofitClient.class) {
                if (retrofit == null) {
                    retrofit = initRetrofit(DEFAULT_RETROFIT);
                }
            }
        }
        return retrofit;
    }

    public static void setReleaseVersion(final boolean isReleaseVersion) {
        QYXXRetrofitClient.isReleaseVersion = isReleaseVersion;
    }


    private static Retrofit initRetrofit(int type) {

        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(sHeaderInterceptor);

        Retrofit ret = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .baseUrl(getBaseUrl(type))
                .build();

        retrofit = ret;
        return retrofit;
    }

    private static String getBaseUrl(int type) {
        String baseUrl;
        switch (type) {
            case DEFAULT_RETROFIT:
                baseUrl = isReleaseVersion ? BASE_URL_RELEASE : BASE_URL_DEBUG;
                break;
            default:
                baseUrl = isReleaseVersion ? BASE_URL_RELEASE : BASE_URL_DEBUG;
                break;
        }
        return baseUrl;
    }

    public static void setChannel(String channel){
        if (sHeaderInterceptor != null) {
            sHeaderInterceptor.sChannelId = channel;
        }
    }

    public static void setSubChannel(String subChannel){
        if (sHeaderInterceptor != null) {
            sHeaderInterceptor.sSubChannelId = subChannel;
        }
    }

    public static void setLanguage(String language){
        if (sHeaderInterceptor != null) {
            sHeaderInterceptor.sLanguage = language;
        }
    }

    public static void setAuthToken(String token){
        if (sHeaderInterceptor != null) {
            sHeaderInterceptor.sAuthToken = token;
        }
    }

    public static void setHeaderInterceptor(HeaderInterceptor interceptor){
        sHeaderInterceptor = interceptor;
    }

    public static void setSignKey(String signkey){
        if (sHeaderInterceptor != null) {
            sHeaderInterceptor.sSignKey = signkey;
        }
    }

    public static void setAccept(String accept){
        if (sHeaderInterceptor != null) {
            sHeaderInterceptor.sAccept = accept;
        }
    }

    public static void clearUserInfo(){
        if (sHeaderInterceptor != null) {
            sHeaderInterceptor.sSignKey = "";
            sHeaderInterceptor.sAuthToken = "";
        }
    }
}
