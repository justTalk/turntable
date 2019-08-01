package com.mm.turntable.model;

/**
 * Created by Liu On 2019/7/30
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class VideoSource {
    int type;
    String rid;
    VideoItem data;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public VideoItem getData() {
        return data;
    }

    public void setData(VideoItem data) {
        this.data = data;
    }
}
