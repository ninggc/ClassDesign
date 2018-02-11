package com.ning.enterprise.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.ning.enterprise.adapter.EmployeeAdapter;
import com.ning.enterprise.info.Employment;

import java.util.LinkedList;

/**
 * Created by ning on 2017/6/10.
 */
public class ShowCollectAty extends AppCompatActivity {
    Gson gson = new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_show_collect);

        initAdapter();
    }

    void initAdapter() {
        SharedPreferences sp = getSharedPreferences("collect", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int i = 1;
        LinkedList<Employment> employments = new LinkedList<>();
        while(true) {
            String line = sp.getString(String.valueOf(i), "");
            if ("".equals(line)) {
                break;
            } else {
                employments.add(gson.fromJson(line, Employment.class));
                ++i;
            }
        }

        ListView lv_showCollect = (ListView) findViewById(R.id.lv_showCollect);

        EmployeeAdapter mEmployeeAdapter = new EmployeeAdapter(ShowCollectAty.this, employments, R.layout.item_employee);

        lv_showCollect.setAdapter(mEmployeeAdapter);

        lv_showCollect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employment employee = (Employment) parent.getItemAtPosition(position);
                Intent intent = new Intent(ShowCollectAty.this, EmployeeDetailAty.class);
                intent.putExtra("employee", employee);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        this.finish();
        super.onPause();
    }
}
