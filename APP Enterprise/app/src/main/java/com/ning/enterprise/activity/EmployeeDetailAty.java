package com.ning.enterprise.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ning.enterprise.info.Employment;

/**
 * Created by ning on 2017/6/6.
 */
public class EmployeeDetailAty extends AppCompatActivity {
    private Employment employment;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private int count = 0;

    private TextView tv_name;
    private TextView tv_title;
    private TextView tv_detail;
    private TextView tv_releaseTime;
    private TextView tv_endTime;
    private ImageButton ibtn_collect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_employee_detail);

        initView();
        initData();
    }

    private void initView() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_detail = (TextView) findViewById(R.id.tv_detail);
        tv_releaseTime = (TextView) findViewById(R.id.tv_releaseTime);
        tv_endTime = (TextView) findViewById(R.id.tv_endTime);
        ibtn_collect = (ImageButton) findViewById(R.id.ibtn_collect);

        ibtn_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp == null) {
                    SharedPreferences sp = getSharedPreferences("collect", MODE_PRIVATE);
                    editor = sp.edit();
                    count = sp.getInt("count", 0);
                }

                editor.putInt("count", ++count);
                editor.putString(String.valueOf(count), employment.toString());
                editor.commit();
                ibtn_collect.setImageDrawable(getResources().getDrawable(R.drawable.nav_collect));
                Toast.makeText(EmployeeDetailAty.this, "收藏成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {

        Intent i = this.getIntent();

        if (i == null) {
            Toast.makeText(EmployeeDetailAty.this, "信息已删除", Toast.LENGTH_SHORT).show();
        } else {
            employment = (Employment) getIntent().getSerializableExtra("employee");

            tv_name.setText(employment.getName());
            tv_title.setText(employment.getTitle());
            tv_detail.setText(employment.getIntroduce());
            tv_releaseTime.setText("发布日期：" + employment.getReleaseTime());
            tv_endTime.setText("截止时间：" + employment.getEndTime());
        }
    }
}
