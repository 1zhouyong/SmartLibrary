package com.example.smartlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartlibrary.R;
import com.example.smartlibrary.bean.MyBorrowBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/5/2
 * Description:{}
 */
public class MyBorrowAdapter extends BaseAdapter {

    private Context context;
    private List<MyBorrowBean.ResultBean> list;
    private CodeCallBack codeCallBack;
    private ReturnCallBack returnCallBack;


    public MyBorrowAdapter(Context context, List<MyBorrowBean.ResultBean> list, CodeCallBack codeCallBack, ReturnCallBack returnCallBack) {
        this.context = context;
        this.list = list;
        this.codeCallBack = codeCallBack;
        this.returnCallBack = returnCallBack;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_myborrow, viewGroup, false);
        }

        ViewHolder holder = new ViewHolder(view);
        holder.tv_name.setText(list.get(i).getName());
        holder.tv_num.setText("条码号:  "+list.get(i).getBookNumber());
        holder.tv_site.setText("馆藏地点:  "+"一层-10"+(i+1));
        holder.tv_startTime.setText("借出日期:"+new SimpleDateFormat("yyyy年MM月dd日").format(list.get(i).getBorrowingTime()));
        holder.tv_stopTime.setText("应还日期:"+new SimpleDateFormat("yyyy年MM月dd日").format(list.get(i).getPreBackTime()));
        holder.btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeCallBack.showCode(i);
            }
        });
        holder.btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnCallBack.returnBook(i);
            }
        });
        return view;
    }

    public static
    class ViewHolder {
        public View rootView;
        public TextView tv_name;
        public TextView tv_num;
        public TextView tv_site;
        public Button btn_code;
        public TextView tv_startTime;
        public TextView tv_stopTime;
        public Button btn_return;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_num = (TextView) rootView.findViewById(R.id.tv_num);
            this.tv_site = (TextView) rootView.findViewById(R.id.tv_site);
            this.btn_code = (Button) rootView.findViewById(R.id.btn_code);
            this.tv_startTime = (TextView) rootView.findViewById(R.id.tv_startTime);
            this.tv_stopTime = (TextView) rootView.findViewById(R.id.tv_stopTime);
            this.btn_return = (Button) rootView.findViewById(R.id.btn_return);
        }

    }
    public interface CodeCallBack{
        void showCode(int position);
    }
    public interface ReturnCallBack{
        void returnBook(int position);
    }
}
