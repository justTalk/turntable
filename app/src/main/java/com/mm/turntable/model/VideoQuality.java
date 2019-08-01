package com.mm.turntable.model;

import java.util.List;

/**
 * Created by Liu On 2019/7/30
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class VideoQuality {
    int use_h265;
    int bit_rate;
    String gear_name;
    String uri;
    List<String> urls;

    public int getUse_h265() {
        return use_h265;
    }

    public void setUse_h265(int use_h265) {
        this.use_h265 = use_h265;
    }

    public int getBit_rate() {
        return bit_rate;
    }

    public void setBit_rate(int bit_rate) {
        this.bit_rate = bit_rate;
    }

    public String getGear_name() {
        return gear_name;
    }

    public void setGear_name(String gear_name) {
        this.gear_name = gear_name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
