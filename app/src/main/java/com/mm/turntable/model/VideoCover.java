package com.mm.turntable.model;

import java.util.List;

/**
 * Created by Liu On 2019/7/30
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class VideoCover {
    String avg_color;
    List<String> url_list;
    String uri;

    public String getAvg_color() {
        return avg_color;
    }

    public void setAvg_color(String avg_color) {
        this.avg_color = avg_color;
    }

    public List<String> getUrl_list() {
        return url_list;
    }

    public void setUrl_list(List<String> url_list) {
        this.url_list = url_list;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
