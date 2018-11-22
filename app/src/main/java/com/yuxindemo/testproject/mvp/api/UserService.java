package com.yuxindemo.testproject.mvp.api;

import com.yuxindemo.testproject.mvp.entity.UserBean;

import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/19.
 */

public interface UserService {

    @POST("ordinarylogin/{phone}/{password}")
    Observable<UserBean> getSearchUser(@Path("phone") String name,
                                       @Path("password") String code);

//    @POST("book/search")
//    Observable<UserBean> getSearchUser(String phone, String code);

}
