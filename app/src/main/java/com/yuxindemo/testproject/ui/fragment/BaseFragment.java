package com.yuxindemo.testproject.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuxindemo.testproject.util.LogUtil;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/5.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private boolean isFirstLoad;//是否是第一次加载
    private boolean isVisible;//是否对用户可见
    private boolean isInitView;//是否初始化控件
    protected View convertView;//显示的converView
    private SparseArray<View> mViews;//管理View的集合

    public abstract int getLayoutID(); //获取布局资源文件
    public abstract void initViews();//初始化控件
    //public abstract void registerListener();//注册监听事件
    public abstract void initData();//初始化数据，如：网络请求获取数据

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViews = new SparseArray<View>();//初始化集合
        convertView = inflater.inflate(getLayoutID(),container,false);//用布局填充器填充布局
        ButterKnife.bind(this, convertView);
        initViews();//初始化控件
        isInitView = true;//已经初始化
        lazyLoad();//懒加载
        //registerListener();
        getFragmentName(); // 得到FragmentName
        return convertView;
    }

    protected void getFragmentName(){
        LogUtil.e("FragmentName"," >>>>>>" + getClass().getSimpleName());
    }

    /**
     * 懒加载
     * 如果不是第一次加载、没有初始化控件、不对用户可见就不加载
     */
    protected  void lazyLoad(){
        //registerListener();//注册监听事件
        if(!isFirstLoad || !isInitView || !isVisible){
            return;
        }
        initData();//初始化数据
        isFirstLoad = false;//已经不是第一次加载
    }

    /**
     * 是否对用户可见
     * @param isVisibleToUser true:表示对用户可见，反之则不可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            isVisible = true;
            //这里根据需求，如果不要每次对用户可见的时候就加载就不要调用lazyLoad()这个方法，根据个人需求
            lazyLoad();
        }else{
            isVisible = false;
        }
    }

//    /**
//     * [设置监听]
//     */
//    public abstract void registerListener();

    /**
     * View点击
     **/
    public abstract void viewsClick(View v);

    /**
     * 将控件的点击事件传递给抽象方法viewsClick,
     * 让子类去实现
     * @param v View
     */
    @Override
    public void onClick(View v) {
        viewsClick(v);
    }




}
