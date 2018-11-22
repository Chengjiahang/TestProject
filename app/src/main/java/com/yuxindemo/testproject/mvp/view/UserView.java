package com.yuxindemo.testproject.mvp.view;

import com.yuxindemo.testproject.mvp.entity.UserBean;

/**
 * Created by Administrator on 2018/7/19.
 */

public interface UserView extends View {
    void onSuccess(UserBean userBean);
    void onError(String result);
}
