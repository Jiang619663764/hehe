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

import com.example.leqiang.modle.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEdtUser;

    private EditText mEdtPsw;

    private Button mBtnFinish;

    private List<UserInfo> mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.common_toolbar);
        setSupportActionBar(toolbar);
        initView();

        //获取用户信息添加到集合中
        String userName=mEdtUser.getText().toString();
        String userPsw=mEdtPsw.getText().toString();
        String userPhone=getIntent().getStringExtra("phone");
        UserInfo info=new UserInfo();
        info.setName(userName);
        info.setPassWord(userPsw);
        info.setPhone(userPhone);
        mUserInfo.add(info);

        mBtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRegSuccess();
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    /**
     * 判断是否注册成功
     */
    private void isRegSuccess() {

    }

    private void initView() {
        mEdtUser=(EditText)findViewById(R.id.edt_user_reg);
        mEdtPsw=(EditText)findViewById(R.id.edt_psw_reg);
        mBtnFinish=(Button)findViewById(R.id.btn_complet_reg);

        mUserInfo=new ArrayList<UserInfo>();
    }

}
