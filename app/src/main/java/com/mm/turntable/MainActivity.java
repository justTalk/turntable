package com.mm.turntable;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mm.turntable.model.VideoSource;
import com.mm.turntable.net.HttpApi;
import com.mm.vviewpager.BasePagerAdapter;
import com.mm.vviewpager.VViewPager;
import com.viva.live.now.up.net.RetrofitCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestData();
    }

    private void requestData(){
        HttpApi.getVideoSource(5, "video", 10, 10, "feed_loadmore", new RetrofitCallback<List<VideoSource>>() {
            @Override
            public void onSuccess(List<VideoSource> videoSources) {
                initViewPager(videoSources);
            }
        });
    }

    private BasePagerAdapter adapter = new BasePagerAdapter<VideoSource>() {
        @Override
        protected void bindView(View view, int position) {
            final VideoSource videoSource = getItem(position);
            Glide.with(MainActivity.this).load(videoSource.getData().getVideo().getCover().getUrl_list().get(0)).into((ImageView) view.findViewById(R.id.cover));
//            SurfaceView surfaceView = view.findViewById(R.id.surface);
//            surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
//                @Override
//                public void surfaceCreated(SurfaceHolder holder) {
//                    final MediaPlayer m = new MediaPlayer();
//                    m.setDisplay(holder);
//                    try {
//                        m.setDataSource(videoSource.getData().getVideo().getDownload_url().get(0));
//                        m.prepare();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    };
//                    m.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                        @Override
//                        public void onPrepared(MediaPlayer mp) {
//                            m.start();
//                        }
//                    });
//                    m.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//                }
//
//                @Override
//                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//                }
//
//                @Override
//                public void surfaceDestroyed(SurfaceHolder holder) {
//
//                }
//            });
        }

        @Override
        protected View createItemView(@NonNull ViewGroup container, int position, int type) {
            return LayoutInflater.from(container.getContext()).inflate(R.layout.item_text, null);
        }
    };

    private void initViewPager(List<VideoSource> videoSources){
        VViewPager vViewPager = findViewById(R.id.vp);
        adapter.addAll(videoSources);
        vViewPager.setAdapter(adapter);
        vViewPager.setOnPageChangeListener(new VViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(VViewPager.class.getName(), "onPageSelected position is " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
