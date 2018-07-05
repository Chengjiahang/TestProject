package com.yuxindemo.testproject.ui.activity;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yuxindemo.testproject.R;

public class MainActivity extends BaseActivity {

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        TextView hello_world_button = findViewById(R.id.hello_world_button);
    }

    @Override
    public void getData(Context mContext) {
        setHeadTitle("MainActivity");
    }

    @Override
    public void widgetClick(View v) {

    }
}
