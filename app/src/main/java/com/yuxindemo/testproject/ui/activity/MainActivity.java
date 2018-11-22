package com.yuxindemo.testproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yuxindemo.testproject.R;
import com.yuxindemo.testproject.adapter.MainPagerAdapter;
import com.yuxindemo.testproject.ui.fragment.FragmentHome;
import com.yuxindemo.testproject.ui.fragment.FragmentLoan;
import com.yuxindemo.testproject.ui.fragment.FragmentNews;
import com.yuxindemo.testproject.ui.fragment.FragmentPersonal;
import com.yuxindemo.testproject.util.BottomNavigationViewHelper;
import com.yuxindemo.testproject.util.LogUtil;
import com.yuxindemo.testproject.widget.MainViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener{


    /**
     * 声明 找ID
     */
    @BindView(R.id.main_viewpager)
    MainViewPager main_viewpager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottom_navigation;

    /**
     * 注入注解 用于对象的声明 （优势就是不用自己New了）
     */
    private FragmentHome mFragmentHome;

    private FragmentLoan mFragmentLoan;

    private FragmentNews mFragmentNews;

    private FragmentPersonal mFragmentPersonal;

    private List<Fragment> framgnets;

    private MenuItem menuItem;

    public static void JUMP(Context context){
        context.startActivity(new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.e("onNewIntent",">>>>>>MainActivity");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        hideHead();
        framgnets = new ArrayList<Fragment>();
        mFragmentHome = new FragmentHome();
        mFragmentLoan = new FragmentLoan();
        mFragmentNews = new FragmentNews();
        mFragmentPersonal = new FragmentPersonal();

        framgnets.add(mFragmentHome);
        framgnets.add(mFragmentLoan);
        framgnets.add(mFragmentNews);
        framgnets.add(mFragmentPersonal);

        main_viewpager.setAnimation(false);
        main_viewpager.addOnPageChangeListener(this);
        main_viewpager.setOffscreenPageLimit(4);
        main_viewpager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), framgnets));

        //系统默认选中第一个,但是系统选中第一个不执行onNavigationItemSelected(MenuItem)方法,如果要求刚进入页面就执行clickTabOne()方法,则手动调用选中第一个
        //bottom_navigation.setSelectedItemId(0);//根据具体情况调用
        // 设置导航栏的类型 disableShiftMode
        BottomNavigationViewHelper.disableShiftMode(bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //BottomNaviationView和ViewPager联动,当BottomNaviationView的某个tab按钮被选中了,同时设置ViewPager对应的页面被选中
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.item_home:
                        main_viewpager.setCurrentItem(0, false);
                        break;//返回true,否则tab按钮不变色,未被选中
                    case R.id.item_loan:
                        main_viewpager.setCurrentItem(1);
                        break;
                    case R.id.item_find:
                        main_viewpager.setCurrentItem(2);
                        break;
                    case R.id.item_my:
                        main_viewpager.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void getData(Context mContext) {
        //setHeadTitle("首页");
    }

    @Override
    public void widgetClick(View v) {
    }

    /**
     * [返回键返回事件]
     *
     * @param keyCode
     * @param event
     */
    private int keyBackClickCount = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            switch (keyBackClickCount++) {
                case 0:
                    Toast.makeText(this, "再次按返回键退出", Toast.LENGTH_SHORT).show();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            keyBackClickCount = 0;
                        }
                    }, 3000);
                    break;
                case 1:
                    finish();
                    break;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            bottom_navigation.getMenu().getItem(0).setChecked(false);
        }
        menuItem = bottom_navigation.getMenu().getItem(position);
        menuItem.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}
