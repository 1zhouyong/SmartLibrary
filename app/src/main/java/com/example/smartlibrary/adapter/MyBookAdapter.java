package com.example.smartlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartlibrary.R;
import com.example.smartlibrary.bean.MyBookBean;
import com.example.smartlibrary.util.GetTime;

import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/5/1
 * Description:{}
 */
public class MyBookAdapter extends BaseAdapter {

    private Context context;
    private List<MyBookBean.ResultBean> resultBeans;

    public MyBookAdapter(Context context, List<MyBookBean.ResultBean> resultBeans) {
        this.context = context;
        this.resultBeans = resultBeans;
    }

    @Override
    public int getCount() {
        return resultBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return resultBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_mybook, viewGroup, false);
        }
        ViewHolder holder = new ViewHolder(view);
        Glide.with(context).load(GetTime.changeImageUrl(resultBeans.get(i).getPicPath())).into(holder.iv_bookpic);
        return view;
    }

    public static
    class ViewHolder {
        public View rootView;
        public ImageView iv_bookpic;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_bookpic = (ImageView) rootView.findViewById(R.id.iv_bookpic);
        }

    }
}
