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

public class RegisterActivity extends AppCompatActivity {

    private EditText mEdtUser;

    private EditText mEdtPsw;

    private Button mBtnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.common_toolbar);
        setSupportActionBar(toolbar);
        initView();
        String userName=mEdtUser.getText().toString();
        String userPsw=mEdtPsw.getText().toString();

        mBtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initView() {
        mEdtUser=(EditText)findViewById(R.id.edt_user_reg);
        mEdtPsw=(EditText)findViewById(R.id.edt_psw_reg);
        mBtnFinish=(Button)findViewById(R.id.btn_complet_reg);
    }

}
