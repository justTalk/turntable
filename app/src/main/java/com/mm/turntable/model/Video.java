package com.mm.turntable.model;

import java.util.List;

/**
 * Created by Liu On 2019/7/30
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class Video {
    String video_id;
    String watermark;
    List<String> url_list;
    boolean allow_cache;
    VideoCover cover;
    String uri;
    List<String> download_url;
    int height;
    int width;
    double duration;
    double preload_size;
    List<VideoQuality> quality_info;
    VideoCover cover_animated;
    VideoCover cover_medium;

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public List<String> getUrl_list() {
        return url_list;
    }

    public void setUrl_list(List<String> url_list) {
        this.url_list = url_list;
    }

    public boolean isAllow_cache() {
        return allow_cache;
    }

    public void setAllow_cache(boolean allow_cache) {
        this.allow_cache = allow_cache;
    }

    public VideoCover getCover() {
        return cover;
    }

    public void setCover(VideoCover cover) {
        this.cover = cover;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<String> getDownload_url() {
        return download_url;
    }

    public void setDownload_url(List<String> download_url) {
        this.download_url = download_url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getPreload_size() {
        return preload_size;
    }

    public void setPreload_size(double preload_size) {
        this.preload_size = preload_size;
    }

    public List<VideoQuality> getQuality_info() {
        return quality_info;
    }

    public void setQuality_info(List<VideoQuality> quality_info) {
        this.quality_info = quality_info;
    }

    public VideoCover getCover_animated() {
        return cover_animated;
    }

    public void setCover_animated(VideoCover cover_animated) {
        this.cover_animated = cover_animated;
    }

    public VideoCover getCover_medium() {
        return cover_medium;
    }

    public void setCover_medium(VideoCover cover_medium) {
        this.cover_medium = cover_medium;
    }
}
