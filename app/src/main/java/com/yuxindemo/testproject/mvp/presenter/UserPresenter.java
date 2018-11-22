package com.yuxindemo.testproject.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.Toast;

import com.yuxindemo.testproject.mvp.entity.UserBean;
import com.yuxindemo.testproject.mvp.manager.UserDataManager;
import com.yuxindemo.testproject.mvp.view.UserView;
import com.yuxindemo.testproject.ui.fragment.FragmentPersonal;
import com.yuxindemo.testproject.util.UserUtil;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2018/7/19.
 */

public class UserPresenter implements Presenter{

    private UserDataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private UserView userView;
    private Intent mIntent;
    private UserBean mUserBean;
    private FragmentPersonal.MyBroadcastReceiver bcr;

    public UserPresenter (Context mContext){
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        manager = new UserDataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        userView = (UserView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
        mIntent = intent;
    }

    public void getSearchBooks(String phone, String code, final Activity context){
        if (!UserUtil.isPhoneNumber(phone)){
            Toast.makeText(context, "请输入正确手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        mCompositeSubscription.add(manager.getSearchUser(phone,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onCompleted() {
                        if (mUserBean != null) {
                            if (mUserBean.getCode().equals("200")){
                                Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
                                CreateBroadcast();
                                SendBroadcast();
                                context.finish();

                            }else {
                                Toast.makeText(context, "登陆失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Toast.makeText(context, "登陆失败，请检查网络", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        mUserBean = userBean;
                    }
                })
        );
    }

    // 动态注册广播
    private void CreateBroadcast(){
        bcr = new FragmentPersonal.MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("myAction");
        mContext.registerReceiver(bcr, filter);
    }

    // 发送广播
    private void SendBroadcast(){
        if(bcr!=null){
            Intent intent = new Intent();
            intent.setAction("myAction");
            intent.putExtra("login", true);
            mContext.sendBroadcast(intent);
            Toast.makeText(mContext, "发送广播", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(mContext, "请先注册广播", Toast.LENGTH_LONG).show();
        }
    }

}
