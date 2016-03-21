package com.example.leqiang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.leqiang.fragment.FirstFragment;
import com.example.leqiang.fragment.ForthFragment;
import com.example.leqiang.fragment.SecondFragment;
import com.example.leqiang.fragment.ThirdFragment;
import com.example.leqiang.view.AutoPlayPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private Toolbar mToolBar;
    private RadioGroup mRadioGroup;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        initView();
        //设置toolbar
        mToolBar = (Toolbar) findViewById(R.id.common_toolbar);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int id = intent.getIntExtra("fragment", 1);
        if (id == 4) {
            mFragmentTransaction.replace(R.id.fl_fragment, new ForthFragment());
        }else{
            mFragmentTransaction.replace(R.id.fl_fragment, new FirstFragment());
        }

        mFragmentTransaction.commit();
//        mFragmentTransaction.replace(R.id.fl_fragment,new FirstFragment());
//        mFragmentTransaction.commit();

        mRadioGroup.setOnCheckedChangeListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {

        mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

    }

    /**
     * RadioButton的点击事件
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (checkedId) {
            case R.id.rb_shouye:
                ft.replace(R.id.fl_fragment, new FirstFragment());
                break;
            case R.id.rb_zhishi:
                ft.replace(R.id.fl_fragment, new SecondFragment());
                break;
            case R.id.rb_tidu:
                ft.replace(R.id.fl_fragment, new ThirdFragment());
                break;
            case R.id.rb_wode:
                ft.replace(R.id.fl_fragment, new ForthFragment());
                break;
        }
        ft.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
