package com.ning.enterprise.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ning.enterprise.adapter.EmployeeAdapter;
import com.ning.enterprise.factory.OkhttpFactory;
import com.ning.enterprise.info.Employment;
import com.ning.enterprise.info.GlobalVar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by ning on 2017/6/6.
 */
public class ShowAty extends AppCompatActivity {
    public static LinkedList<Employment> employments;
    ListView lv_list;

    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_show);

        initView();
        initData();
    }

    private void initView() {
        lv_list = (ListView) findViewById(R.id.list);

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String msg = parent.getItemAtPosition(position).toString();

                Employment employee = (Employment) parent.getItemAtPosition(position);
                Intent intent = new Intent(ShowAty.this, EmployeeDetailAty.class);
                intent.putExtra("employee", employee);
                startActivity(intent);
            }
        });
    }

    private void initData() {
//        /*定义一个动态数组*/
//        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
//        /*在数组中存放数据*/
//        for(int i=1;i<11;i++)
//        {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("ItemTitle", "第"+i+"行");
//            map.put("ItemBody", "body");//加入图片
//            map.put("ItemTime", "time");
//            listItem.add(map);
//        }

//        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,listItem,//需要绑定的数据
//                R.layout.item_employee,//每一行的布局
//                //动态数组中的数据源的键对应到定义布局的View中
//                new String[] {"ItemTime", "ItemBody", "ItemTime"},
//                new int[] {R.id.tv_title, R.id.tv_body, R.id.tv_endTime}
//        );

//        employments = new LinkedList<Employment>();
//        for (int i = 0; i < 10; i++) {
//            employments.add(new Employment(
//                    i + "",
//                    "title",
//                    "start",
//                    "end",
//                    "这是第" + i + "行"
//            ));
//        }

        new OkhttpFactory() {
            @Override
            public void onResponse(Message msg) {
                String json = msg.obj.toString();

                LinkedList<Employment> employments = gson.fromJson(json, new TypeToken<LinkedList<Employment>>(){}.getType());

                EmployeeAdapter mEmployeeAdapter = new EmployeeAdapter(ShowAty.this, employments, R.layout.item_employee);

                lv_list.setAdapter(mEmployeeAdapter);
            }
        }.send(GlobalVar.url + "selectAllEmp", "select");

    }
}
