package com.yuxindemo.testproject.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yuxindemo.testproject.R;
import com.yuxindemo.testproject.ui.activity.LoginActivity;
import com.yuxindemo.testproject.util.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/5.
 */

public class FragmentPersonal extends BaseFragment{

//    @BindView(R.id.fragment_personal_view)
//    LinearLayout fragment_personal_view;
    @BindView(R.id.off_login)
    TextView off_login;

    private boolean isLogin;

    @Override
    public int getLayoutID() {
        if (isLogin) {
            return R.layout.fragment_personal;
        } else {
            return R.layout.fragment_personal_login;
        }

    }

    @Override
    public void initViews() {

        if (isLogin) {
            //fragment_personal_view.addView();
        } else {

        }

    }

    @Override
    public void initData() {
        LogUtil.e("initData",">>>>>>FragmentPersonal");
    }

    @OnClick({R.id.off_login})
    @Override
    public void viewsClick(View v) {
        switch (v.getId()) {
            case R.id.off_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;

            default:
                break;
        }
    }

    public static class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtil.e("onReceive", intent.getStringExtra("data"));
            Toast.makeText(context, "onReceive>>>" + intent.getStringExtra("data"), Toast.LENGTH_SHORT).show();
            boolean aa;
            if (intent.getBooleanExtra("login", true)){

            }
        }

    }

}
