package com.yuxindemo.testproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.yuxindemo.testproject.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/22.
 */

public class SingleTaskActivity extends BaseActivity {

    @BindView(R.id.jump_main)
    Button jump_main;
    @BindView(R.id.jump_single_top)
    Button jump_single_top;

    public static void JUMP(Context context){
        context.startActivity(new Intent(context, SingleTaskActivity.class));
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_singletask;
    }

    @Override
    public void initView() {
        setHeadTitle("SingleTaskActivity");
    }

    @Override
    public void getData(Context mContext) {

    }

    @OnClick({R.id.jump_main, R.id.jump_single_top})
    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.jump_main:
                MainActivity.JUMP(this);
                break;
            case R.id.jump_single_top:
                SingleTopActivity.JUMP(this);
                break;
            default:
                break;
        }
    }
}
