package com.yuxindemo.testproject.mvp;

import android.content.Context;

import com.yuxindemo.testproject.mvp.api.UserService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/7/19.
 */

public class RetrofitHelper{

    private Context mContext;

    OkHttpClient client = new OkHttpClient();
    //GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    public static RetrofitHelper getInstance(Context context){
        if (instance == null){
            instance = new RetrofitHelper(context);
        }
        return instance;
    }
    private RetrofitHelper(Context context){
        mContext = context;
        init();
    }

    private void init(){
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://47.92.38.87:8082/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    public UserService getServer(){
        return mRetrofit.create(UserService.class);
    }


}
