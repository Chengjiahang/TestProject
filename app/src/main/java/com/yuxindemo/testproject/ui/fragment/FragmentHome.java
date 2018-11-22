package com.yuxindemo.testproject.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.yuxindemo.testproject.R;
import com.yuxindemo.testproject.ui.activity.MainActivity;
import com.yuxindemo.testproject.ui.activity.SingleTopActivity;
import com.yuxindemo.testproject.util.LogUtil;
import com.yuxindemo.testproject.widget.XListView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/5.
 */

public class FragmentHome extends BaseFragment {

    @BindView(R.id.fragment_home_lv)
    XListView fragment_home_lv;
    @BindView(R.id.jump_main)
    Button jump_main;
    @BindView(R.id.jump_single)
    Button jump_single;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews() {
        //fragment_home_lv.addFooterView();
    }

//    @Override
//    public void registerListener() {
//
//    }

    @Override
    public void initData() {
        LogUtil.e("initData",">>>>>>FragmentHome");
    }

    @OnClick({R.id.jump_single, R.id.jump_main})
    @Override
    public void viewsClick(View view) {
        switch (view.getId()) {
            case R.id.jump_single:
                startActivity(new Intent(getActivity(), SingleTopActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.jump_main:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            default:
                break;
        }
    }
}
