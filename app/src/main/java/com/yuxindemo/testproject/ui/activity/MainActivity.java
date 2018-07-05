package com.yuxindemo.testproject.ui.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.yuxindemo.testproject.R;
import com.yuxindemo.testproject.adapter.MainPagerAdapter;
import com.yuxindemo.testproject.ui.fragment.FragmentHome;
import com.yuxindemo.testproject.ui.fragment.FragmentLoan;
import com.yuxindemo.testproject.ui.fragment.FragmentNews;
import com.yuxindemo.testproject.ui.fragment.FragmentPersonal;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    /**
     * 注入注解 用于对象的声明 （优势就是不用自己New了）
     */
    @Inject
    MainPagerAdapter adapter;
    @Inject
    List<Fragment> framgnets;
    @Inject
    FragmentHome mFragmentHome;
    @Inject
    FragmentLoan mFragmentLoan;
    @Inject
    FragmentNews mFragmentNews;
    @Inject
    FragmentPersonal mFragmentPersonal;

    /**
     * 声明 找ID
     */
    @BindView(R.id.hello_world_button)
    TextView hello_world_button;
    @BindView(R.id.main_viewpager)
    ViewPager main_viewpager;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        framgnets.add(mFragmentHome);
        framgnets.add(mFragmentLoan);
        framgnets.add(mFragmentNews);
        framgnets.add(mFragmentPersonal);
        adapter = new MainPagerAdapter(getSupportFragmentManager(), framgnets);
        main_viewpager.setAdapter(adapter);
    }

    @Override
    public void getData(Context mContext) {
        setHeadTitle("MainActivity");
    }

    @Override
    public void widgetClick(View v) {

    }
}
