package com.yuxindemo.testproject.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yuxindemo.testproject.util.LogUtil;

/**
 * Created by Administrator on 2018/9/14.
 */

public class MyBroadcastReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.e("onReceive", intent.getStringExtra("data"));
    }

}

//public class MyBroadcastReceiver extends BroadcastReceiver{
//
//    private interface_callback mCallback;
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        LogUtil.e("onReceive", intent.getStringExtra("data"));
//        if (mCallback!=null){
//            mCallback.execute(true);
//        }
//    }
//
//    public void Send(interface_callback callback){
//        this.mCallback = callback;
//        //callback.execute(true);
//    }
//
//}