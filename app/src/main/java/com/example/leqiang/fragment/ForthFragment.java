package com.example.leqiang.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.style.UpdateAppearance;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leqiang.LoginActivity;
import com.example.leqiang.R;


public class ForthFragment extends Fragment {

    private Button mClickLog;
    private ImageView mUserPicture;
    private TextView mUserName;
    public ForthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.layout_fragment_forth, container, false);
        initView(view);
        mClickLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }

    private void initView(View view) {
        mClickLog=(Button)view.findViewById(R.id.btn_login_forth);
        mUserName=(TextView)view.findViewById(R.id.name_login_forth);
        mUserPicture=(ImageView)view.findViewById(R.id.user_picture_login);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(LoginActivity.isLogin){
            mClickLog.setVisibility(View.GONE);
            mUserName.setVisibility(View.VISIBLE);
            mUserPicture.setVisibility(View.VISIBLE);
        }
    }

}
