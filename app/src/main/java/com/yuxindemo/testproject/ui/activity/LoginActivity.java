package com.yuxindemo.testproject.ui.activity;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/18.
 */

public class LoginActivity extends BaseActivity{

    @BindView(R.id.login_phone)
    TextView login_phone;
    @BindView(R.id.login_phone_et)
    EditText login_phone_et;
    @BindView(R.id.login_code_et)
    EditText login_code_et;
    @BindView(R.id.login_bt)
    Button login_bt;

    private UserPresenter mUserPresenter = new UserPresenter(this);

    @Override
    public int bindLayout() {
        return R.layout.login_activity;
    }

    @Override
    public void initView() {
        // 初始化 界面布局
        setHeadTitle(getResources().getString(R.string.login_activity_title));
    }

    @Override
    public void getData(Context mContext) {
        // 初始化创建 Presenter
        mUserPresenter.onCreate();
        //mUserPresenter.attachView(mUserView);
    }

    private UserView mUserView = new UserView() {
        @Override
        public void onSuccess(UserBean userBean) {
            //mUserView.onSuccess(userBean);
        }

        @Override
        public void onError(String result) {
            Toast.makeText(LoginActivity.this,result, Toast.LENGTH_SHORT).show();
        }
    };

    @OnClick({R.id.login_bt})
    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.login_bt:
                    mUserPresenter.getSearchBooks(login_phone_et.getText().toString(), PrivateUtil.MD5(login_code_et.getText().toString()), this);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mUserPresenter.onStop();
    }

}
