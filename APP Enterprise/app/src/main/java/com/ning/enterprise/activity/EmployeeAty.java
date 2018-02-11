package com.ning.enterprise.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ning.enterprise.factory.OkhttpFactory;
import com.ning.enterprise.info.Employment;
import com.ning.enterprise.info.GlobalVar;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ning on 2017/6/6.
 */
public class EmployeeAty extends AppCompatActivity {
    EditText et_title;
    EditText et_body;
    TextView tv_endTime;
    Button btn_release;

    Employment emp = new Employment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_employee);
        initView();
    }

    void initView() {
        final DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
        final Calendar calendar = Calendar.getInstance(Locale.CHINA);

        et_title = (EditText) findViewById(R.id.et_title);
        et_body = (EditText) findViewById(R.id.et_body);

        tv_endTime = (TextView) findViewById(R.id.tv_endTime);
//        tv_endTime.setText(new Date().toString());
        tv_endTime.setText(fmtDateAndTime.format(new Date()));
        tv_endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //获取日期格式器对象

                Calendar now = Calendar.getInstance(Locale.CHINA);
                final Calendar c = Calendar.getInstance(Locale.CHINA);
                new DatePickerDialog(EmployeeAty.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                c.set(Calendar.YEAR, year);
                                c.set(Calendar.MONTH, month);
                                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                emp.setEndTime(new Timestamp(c.getTimeInMillis()).toString());
                                emp.setReleaseTime(new Timestamp(c.getTimeInMillis()).toString());
                                ((TextView) v).setText(fmtDateAndTime.format(c.getTime()));
                            }
                        }
                        , now.get(Calendar.YEAR)
                        , now.get(Calendar.MONTH)
                        , now.get(Calendar.DAY_OF_MONTH)

                ).show();
            }
        });

        btn_release = (Button) findViewById(R.id.btn_release);
        btn_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emp.setTitle(et_title.getText().toString());
                emp.setIntroduce(et_body.getText().toString());
                emp.setName(MainActivity.nickname);


                new OkhttpFactory() {
                    @Override
                    public void onResponse(Message msg) {
                        Log.e("------", "onResponse: " + msg.obj);
                        //这里写发送成功之后要做的
                    }
                }.send(GlobalVar.url + "add", emp.toJSON());
            }
        });
    }
}
