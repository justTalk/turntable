package com.mm.turntable.net;

import com.mm.turntable.model.VideoSource;
import com.viva.live.now.up.net.base.BaseDataWrapper;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Liu On 2019/7/30
 * Description:
 * email: mingming.liu@quvideo.com
 */
public interface HttpService {

    @GET("hotsoon/feed/")
    Flowable<BaseDataWrapper<List<VideoSource>>> getVideoSource(@Query("tab_id") int tabId, @Query("type") String type,
                                                                @Query("offset") int offset, @Query("count") int count,
                                                                @Query("req_from") String req_from, @QueryMap Map<String, String > map);
}
