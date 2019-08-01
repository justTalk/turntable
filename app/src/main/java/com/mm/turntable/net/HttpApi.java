package com.mm.turntable.net;

import com.mm.turntable.model.VideoSource;
import com.viva.live.now.up.net.APIServiceFactory;
import com.viva.live.now.up.net.BaseCallProxy;
import com.viva.live.now.up.net.RetrofitCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liu On 2019/7/30
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class HttpApi {

    /**
     * @param req_from feed_loadmore ||
     */
    public static void getVideoSource(int tabId, String type,
                                      int offset, int count, String req_from, RetrofitCallback<List<VideoSource>> retrofitCallback){
        BaseCallProxy.Builder.newInstance(APIServiceFactory.getServiceInstance(HttpService.class).getVideoSource(tabId, type, offset, count, req_from, createMap()),
                retrofitCallback).doSubscribe();

    }


    public static Map<String, String> createMap(){
        Map<String, String> autoParam = new HashMap<>();
        autoParam.put("max_time", String.valueOf(System.currentTimeMillis()));
        autoParam.put("diff_stream", String.valueOf(1));
        autoParam.put("hb_info", "");
        autoParam.put("gaid", "20bc6c35-e5f4-4634-a309-d1ae31c6f1b3");
        autoParam.put("ad_user_agent", "Mozilla%2F5.0+%28Linux%3B+Android+8.1.0%3B+Nexus+5X+Build%2FOPM3.171019.016%3B+wv%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Version%2F4.0+Chrome%2F75.0.3770.143+Mobile+Safari%2F537.36");
        autoParam.put("feed_video_gap", "0");
        autoParam.put("live_sdk_version", "700");
        autoParam.put("iid", "6719333975595321090");
        autoParam.put("device_id", "6719333452649399809");
        autoParam.put("ac", "wifi");
        autoParam.put("channel", "google_play");
        autoParam.put("aid", "1145");
        autoParam.put("app_name", "live_i18n");
        autoParam.put("version_code", "700");
        autoParam.put("version_name", "7.0.0");
        autoParam.put("device_platform", "android");
        autoParam.put("ssmix", "a");
        autoParam.put("device_type", "Nexus+5X");
        autoParam.put("device_brand", "google");
        autoParam.put("language", "zh");
        autoParam.put("os_api", "27");
        autoParam.put("os_version", "8.1");
        autoParam.put("openudid", "ceef0c587dff6a96");
        autoParam.put("manifest_version_code", "7006");
        autoParam.put("resolution", "1080*1794");
        autoParam.put("dpi", "420dp");
        autoParam.put("update_version_code", "7006");
        autoParam.put("_rticket", "1564469722492");
        autoParam.put("ab_version", "864023%2C523365%2C889039%2C958601%2C1047276%2C860124%2C954877%2C845305%2C1019398%2C1045867%2C1047130%2C1045323%2C1046733");
        autoParam.put("client_version_code", "700");
        autoParam.put("sys_lang", "zh_TW_%23Hant");
        autoParam.put("tz_name", "Asia%2FShanghai");
        autoParam.put("tz_offset", "28800");
        autoParam.put("user_region", "HK");
        autoParam.put("locale", "zh_TW_%23Hant");
        autoParam.put("new_nav", "0");
        autoParam.put("ws_status", "CONNECTED");
        autoParam.put("ts", "1564469722");
        return autoParam;
    }
}
