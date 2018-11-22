package com.yuxindemo.testproject.mvp.presenter;

import android.content.Intent;
import android.view.View;

/**
 * Created by Administrator on 2018/7/19.
 */

public interface Presenter {

    void onCreate();

    void onStart();

    void onStop();

    void pause();

    void attachView(View view);

    void attachIncomingIntent(Intent intent);

}
