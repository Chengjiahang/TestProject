package com.yuxindemo.testproject.ui.activity;

/**
 * Created by Administrator on 2018/7/4.
 */


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuxindemo.testproject.R;
import com.yuxindemo.testproject.util.LruJsonCache;

import butterknife.ButterKnife;

/**
 * 基类Activity
 */

public abstract  class BaseActivity extends AppCompatActivity implements View.OnClickListener  {

    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = false;
    /**
     * 是否允许全屏
     **/
    private boolean mAllowFullScreen = true;
    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isAllowScreenRotate = false;
    /**
     * 数据读取进度条
     **/
    //protected LoadingView load_view;

    protected TextView tv_title, tv_right_btn;
    protected ImageView img_left_btn, img_right_btn;
    protected Context context;
    protected LruJsonCache lruJsonCache;//缓存框架
    protected ProgressDialog pd;
    protected boolean isActivityRunning = false;


    public RelativeLayout headView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 设置不横屏
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (isSetStatusBar) {
            steepStatusBar();
        }
        context = this;
        baseSetContentView();

        ButterKnife.bind(this);

        if (!isAllowScreenRotate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        //创建LruJsonCache组件
        lruJsonCache = LruJsonCache.get(this);

        initView();

        getData(this);
    }

    /**
     * [绑定基类布局]
     *
     * @return
     */
    public void baseSetContentView() {
        setContentView(R.layout.ac_base);
        FrameLayout layout = (FrameLayout) findViewById(R.id.base_content);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(bindLayout(), null);
        layout.addView(view);
        if (Build.VERSION.SDK_INT >= 21) {
            setStatusBarUpperAPI21();
        } else {

        }
        initHead();

    }

    /**
     * 初始化头部标题栏和数据读取界面
     **/
    public void initHead() {
        //标题栏
        headView = findViewById(R.id.base_header);
        tv_title = (TextView) findViewById(R.id.tv_title);
        img_left_btn = (ImageView) findViewById(R.id.img_left_btn);
        img_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void hideHead() {
        headView.setVisibility(View.GONE);
    }

    /**
     * 设置标题名称
     **/
    public void setHeadTitle(String value) {
        if(headView.getVisibility()==View.GONE){
            headView.setVisibility(View.VISIBLE);
        }
        tv_title.setText(value);
    }

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * [初始化控件]
     */
    public abstract void initView();

    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void getData(Context mContext);


    /**
     * View点击
     **/
    public abstract void widgetClick(View v);


    @Override
    public void onClick(View v) {
        widgetClick(v);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
//        isActivityRunning = false;
        super.onDestroy();
    }

    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * [是否允许屏幕旋转]
     *
     * @param isAllowScreenRotate
     */
    public void setScreenRoate(boolean isAllowScreenRotate) {
        this.isAllowScreenRotate = isAllowScreenRotate;
    }


    /**
     * [是否允许全屏]
     *
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * [是否设置沉浸状态栏]
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }



    public void showLoadingDialog(final String msg) {
//        if (!isActivityRunning) {
        if (pd == null)
            pd = new ProgressDialog(context);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });
        pd.setMessage(msg);
        pd.show();
//        }
    }

    public void dismissDialog() {
//        if (isActivityRunning) {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
//            }
        }
    }

    private void setStatusBarUpperAPI21(){
        Window window = getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(getResources().getColor(R.color.tarmac));
        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
            ViewCompat.setFitsSystemWindows(mChildView, true);
        }
    }

}
