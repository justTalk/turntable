package com.mm.turntable.model;

/**
 * Created by Liu On 2019/7/30
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class VideoItem {
    String extra_scheme_url;
    String weibo_share_title;
    String share_title;
    String cell_width;
    String share_strong_guide;
    String description;
    Video video;
    long id;
    States stats;
    String share_url;
    String title;
    String extra_share_scheme_url;

    public String getExtra_scheme_url() {
        return extra_scheme_url;
    }

    public void setExtra_scheme_url(String extra_scheme_url) {
        this.extra_scheme_url = extra_scheme_url;
    }

    public String getWeibo_share_title() {
        return weibo_share_title;
    }

    public void setWeibo_share_title(String weibo_share_title) {
        this.weibo_share_title = weibo_share_title;
    }

    public String getShare_title() {
        return share_title;
    }

    public void setShare_title(String share_title) {
        this.share_title = share_title;
    }

    public String getCell_width() {
        return cell_width;
    }

    public void setCell_width(String cell_width) {
        this.cell_width = cell_width;
    }

    public String getShare_strong_guide() {
        return share_strong_guide;
    }

    public void setShare_strong_guide(String share_strong_guide) {
        this.share_strong_guide = share_strong_guide;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public States getStats() {
        return stats;
    }

    public void setStats(States stats) {
        this.stats = stats;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtra_share_scheme_url() {
        return extra_share_scheme_url;
    }

    public void setExtra_share_scheme_url(String extra_share_scheme_url) {
        this.extra_share_scheme_url = extra_share_scheme_url;
    }
}
