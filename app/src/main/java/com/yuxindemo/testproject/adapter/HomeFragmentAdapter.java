package com.yuxindemo.testproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/12/30.
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {

    public List<Fragment> fragmentList = new ArrayList<Fragment>();

    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public HomeFragmentAdapter(FragmentManager fm, List<Fragment> fragments){
        super(fm);
        this.fragmentList=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
