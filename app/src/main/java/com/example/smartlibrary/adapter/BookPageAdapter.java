package com.example.smartlibrary.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/4/26
 * Description:{}
 */
public class BookPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String[] pagerTitle;

    public BookPageAdapter(FragmentManager fm,List<Fragment> fragments,String[] pagerTitle) {
        super(fm);
        this.fragments =fragments;
        this.pagerTitle = pagerTitle;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pagerTitle[position];
    }
}
