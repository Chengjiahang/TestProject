package com.yuxindemo.testproject.ui.fragment;

import android.view.View;

import com.yuxindemo.testproject.R;
import com.yuxindemo.testproject.util.LogUtil;

/**
 * Created by Administrator on 2018/7/5.
 */

public class FragmentNews extends BaseFragment {
    @Override
    public int getLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    public void initViews() {

    }

//    @Override
//    public void registerListener() {
//
//    }

    @Override
    public void initData() {
        LogUtil.e("initData",">>>>>>FragmentNews");
    }

    @Override
    public void viewsClick(View view) {

    }
}
