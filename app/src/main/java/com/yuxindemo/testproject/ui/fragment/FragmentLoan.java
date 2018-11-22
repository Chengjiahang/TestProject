package com.yuxindemo.testproject.ui.fragment;

import android.view.View;

import com.yuxindemo.testproject.R;
import com.yuxindemo.testproject.util.LogUtil;

/**
 * Created by Administrator on 2018/7/5.
 */

public class FragmentLoan extends BaseFragment {

    @Override
    public int getLayoutID() {
        return R.layout.fragment_loan;
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
        LogUtil.e("initData",">>>>>>FragmentLoan");
    }

    @Override
    public void viewsClick(View view) {

    }
}
