package com.example.leqiang;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.leqiang.fragment.ForthFragment;

import java.util.HashMap;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 判断是否已经登录
     */
    public static boolean isLogin;
    /**
     * 注册按钮
     */
    private Button mBtnReg;

    /**
     * 忘记密码按钮
     */
    private Button mBtnForget;

    /**
     * 用户名编辑框
     */
    private EditText mEdtUser;

    /**
     * 密码编辑框
     */
    private EditText mEdtPsw;

    /**
     * 登录按钮
     */
    private Button mBtnLogin;

    /**
     * 短信验证的KEY
     */
    private static String APP_KEY = "10a71b20e0ee8";

    private static String APP_SECRET = "a8365658e019a4cbf9f90128937d23e2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);
        //初始化短信SDK
        SMSSDK.initSDK(this, APP_KEY, APP_SECRET);

        Toolbar toolbar = (Toolbar) findViewById(R.id.common_toolbar);
        setSupportActionBar(toolbar);
        initView();
        //设置点击事件
        mBtnLogin.setOnClickListener(this);
        mBtnReg.setOnClickListener(this);
        mBtnForget.setOnClickListener(this);

    }

    /**
     * 提交数据到SMSSDK中
     *
     * @param country 国家
     * @param phone   电话
     */
    public void submitUserInfo(String country, String phone) {
        Random r = new Random();
        String uid = Math.abs(r.nextInt()) + "";
        String nickName = "jp";
        SMSSDK.submitUserInfo(uid, nickName, null, country, phone);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mBtnReg = (Button) findViewById(R.id.sign_up_login);
        mBtnForget = (Button) findViewById(R.id.forget_psw_login);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mEdtUser = (EditText) findViewById(R.id.edt_user_login);
        mEdtPsw = (EditText) findViewById(R.id.edt_psw_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login://登录
                checkInfo();
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("fragment",4);
                startActivity(intent);
                isLogin = true;
                finish();
                break;
            case R.id.forget_psw_login:
                break;
            case R.id.sign_up_login://短信验证工能
                final RegisterPage registerPage = new RegisterPage();
                //注册回调事件
                registerPage.setRegisterCallback(new EventHandler() {
                    //时间完成后调用
                    @Override
                    public void afterEvent(int i, int i1, Object o) {
                        if (i1 == SMSSDK.RESULT_COMPLETE) {
                            // 获取数据
                            HashMap<String, Object> maps = (HashMap<String, Object>) o;
                            // 获取信息
                            String country = (String) maps.get("country");
                            String phone = (String) maps.get("phone");
                            submitUserInfo(country, phone);
                            Intent intent = new Intent(LoginActivity.this,
                                    RegisterActivity.class);
                            intent.putExtra("phone", phone);
                            registerPage.startActivity(intent);
                        }
                    }
                });
                registerPage.show(this);
                break;
        }
    }

    /**
     * 检查登录信息是否正确
     */
    private void checkInfo() {

    }
}
