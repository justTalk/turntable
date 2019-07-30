package com.mm.vviewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Liu On 2019/6/19
 * Description:ViewPager基础适配器
 * email: mingming.liu@quvideo.com
 */
public abstract class BasePagerAdapter<T> extends PagerAdapter {

    private List<T> mData = new ArrayList<>();
    SparseArray<Stack<View>> mCachedView = new SparseArray<>();

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View newObject = null;
        int type = getType(position);
        if (!isUseCache() || mCachedView.get(type) == null || mCachedView.get(type).isEmpty()) {
            newObject = createItemView(container, position, getType(position));
        }else {
            newObject = mCachedView.get(type).pop();
        }
        container.addView(newObject);
        bindView(newObject, position);
        return newObject;
    }

    protected abstract void bindView(View view, int position);

    protected abstract View createItemView(@NonNull ViewGroup container, int position, int type);

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (isUseCache()) {
            View convertView = (View) object;
            container.removeView(convertView);
            addCachedView(convertView, getType(position));
        }
    }

    protected void addCachedView(View willCachedView, int type){
        Stack stack = mCachedView.get(type);
        if (stack == null) {
            stack = new Stack();
            mCachedView.put(type, stack);
        }
        stack.push(willCachedView);
    }

    public T getItem(int position){
        return mData.get(position);
    }

    public int getType(int position){
        return 1;
    }

    public boolean isUseCache(){
        return true;
    }

    public void addAll(List all){
        this.mData.addAll(all);
        notifyDataSetChanged();
    }

    public void add(int index, T item){
        this.mData.add(index, item);
        this.notifyDataSetChanged();
    }
}
