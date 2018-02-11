package com.ning.enterprise.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ning on 2017/6/7.
 */
public class UserCenterAty extends AppCompatActivity {
    Button btn_showCollect;
    Button btn_exitLogin;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_user_center);

        initData();
        initView();
    }

    void initView() {
        btn_showCollect = (Button) findViewById(R.id.btn_showCollect);
        btn_showCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserCenterAty.this, ShowCollectAty.class));
            }
        });

        btn_exitLogin = (Button) findViewById(R.id.btn_exitLogin);
        btn_exitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                Bundle b = new Bundle();
                b.putString("key", "exit");
                i.putExtras(b);
                setResult(RESULT_OK, i);
                UserCenterAty.this.finish();
            }
        });
    };

    void initData() {
        sp = getSharedPreferences("center", MODE_PRIVATE);
        editor = sp.edit();
        if (!"".equals(sp.getString("user", ""))) {
            editor.putString("user", "");
            editor.commit();
        }
    };
}
