package com.yuxindemo.testproject.mvp.manager;

import android.content.Context;

import com.yuxindemo.testproject.mvp.RetrofitHelper;
import com.yuxindemo.testproject.mvp.api.UserService;
import com.yuxindemo.testproject.mvp.entity.UserBean;

import rx.Observable;

/**
 * Created by Administrator on 2018/7/19.
 */

public class UserDataManager {

    private UserService mUserService;

    public UserDataManager(Context context){
        this.mUserService = RetrofitHelper.getInstance(context).getServer();
    }
    public Observable<UserBean> getSearchUser(String phone, String code){
        return mUserService.getSearchUser(phone, code);
    }
}
