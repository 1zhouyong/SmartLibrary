package com.example.smartlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartlibrary.R;
import com.example.smartlibrary.activity.MainActivity;
import com.example.smartlibrary.bean.SeatInfomationBean;
import com.example.smartlibrary.util.GetTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * author: ${周勇}
 * Date: 2020/4/14
 * Description:{}
 */
public class SeatInfomationAdpater extends BaseAdapter {

    private Context context;
    private List<SeatInfomationBean> seatInfomationBeanList;
    private CancelCallBack cancelCallBack;

    public SeatInfomationAdpater(Context context, List<SeatInfomationBean> seatInfomationBeanList, CancelCallBack cancelCallBack) {
        this.context = context;
        this.seatInfomationBeanList = seatInfomationBeanList;
        this.cancelCallBack = cancelCallBack;
    }

    @Override
    public int getCount() {
        return seatInfomationBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return seatInfomationBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_seat_info, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tv_time.setText(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        viewHolder.tv_order_num.setText("订单编号：#" + getRandom(6));
        viewHolder.tv_name.setText("客户：" + MainActivity.userName);
        viewHolder.tv_student_num.setText("学号：" + MainActivity.studentNumber);
        viewHolder.tv_site.setText("空间预约：" + seatInfomationBeanList.get(position).getResult().getPlace() + "——" + seatInfomationBeanList
                .get(position).getResult().getNumber());
        viewHolder.tv_date.setText("时间段：" + GetTime.getNowDay(position) + "08:00-20:00");
        viewHolder.btn_cancel_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelCallBack.cancelSite(position);
            }
        });
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView tv_time;
        public TextView tv_order_num;
        public TextView tv_name;
        public TextView tv_student_num;
        public TextView tv_site;
        public TextView tv_date;
        public Button btn_cancel_site;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_order_num = (TextView) rootView.findViewById(R.id.tv_order_num);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_student_num = (TextView) rootView.findViewById(R.id.tv_student_num);
            this.tv_site = (TextView) rootView.findViewById(R.id.tv_site);
            this.tv_date = (TextView) rootView.findViewById(R.id.tv_date);
            this.btn_cancel_site = (Button) rootView.findViewById(R.id.btn_cancel_site);
        }

    }

    private long getRandom(long n) {
        long min = 1, max = 9;
        for (int i = 1; i < n; i++) {
            min *= 10;
            max *= 10;
        }
        long rangeLong = (((long) (new Random().nextDouble() * (max - min)))) + min;
        return rangeLong;
    }

    public interface CancelCallBack {
        void cancelSite(int position);
    }


}
