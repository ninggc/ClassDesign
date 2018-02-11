package com.ning.enterprise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ning.enterprise.activity.R;
import com.ning.enterprise.info.Employment;

import java.util.LinkedList;

/**
 * Created by ning on 2017/6/10.
 */
public class EmployeeAdapter extends BaseAdapter {
    private LinkedList<Employment> employments;
    private int resource;
    private LayoutInflater inflater;

    public EmployeeAdapter(Context context, LinkedList<Employment> employments, int resource) {
        this.employments = employments;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return employments.size();
    }

    @Override
    public Object getItem(int position) {
        return employments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
		
//		convertView = LayoutInflater.from(getApplicationContext()).inflate  
//            (R.layout.baseadapter_provider, null);  

        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(resource, null);//生成绑定数据的View界面
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_body = (TextView) convertView.findViewById(R.id.tv_body);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_endTime);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder ) convertView.getTag();
        }

        Employment employment = employments.get(position);
        //下面代码实现数据绑定
        holder.tv_time.setText(employment.getReleaseTime());
        holder.tv_body.setText(employment.getIntroduce());
        holder.tv_title.setText(employment.getTitle());

        return convertView;
    }
    /**
     *保存findViewById获得的子控件索引的内部类
     */

    static class ViewHolder {
        public TextView tv_title;
        public TextView tv_body;
        public TextView tv_time;
    }

}
