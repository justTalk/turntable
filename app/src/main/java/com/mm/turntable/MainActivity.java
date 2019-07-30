package com.mm.turntable;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mm.vviewpager.VViewPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
    }

    private void initViewPager(){
        VViewPager vViewPager = findViewById(R.id.vp);
        vViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View v = LayoutInflater.from(container.getContext()).inflate(R.layout.item_text, null);
                ((TextView)v.findViewById(R.id.content)).setText("我是第 " + position + " 个View");
                v.findViewById(R.id.content).setBackgroundColor(getColor(position));
                container.addView(v);
                return v;
            }

            private int getColor(int position){
                int[] colors = new int[]{R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark};
                return getResources().getColor(colors[position % 3]);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
    }
}
