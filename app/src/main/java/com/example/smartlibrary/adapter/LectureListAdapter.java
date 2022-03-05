package com.example.smartlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartlibrary.R;
import com.example.smartlibrary.bean.LectureListBean;

/**
 * author: ${周勇}
 * Date: 2020/4/18
 * Description:{}
 */
public class LectureListAdapter extends BaseAdapter {

    private Context context;
    private LectureListBean lectureListBean;

    public LectureListAdapter(Context context, LectureListBean lectureListBean) {
        this.context = context;
        this.lectureListBean = lectureListBean;
    }


    @Override
    public int getCount() {
        return lectureListBean.getResult().size();
    }

    @Override
    public Object getItem(int i) {
        return lectureListBean.getResult().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LectureListBean.ResultBean resultBean = lectureListBean.getResult().get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_lecture, viewGroup,false);

        }
        ViewHolder holder = new ViewHolder(view);
        holder.tv_lecture_name.setText(resultBean.getName());
        holder.tv_lecture_date.setText(resultBean.getOrderNumber()+"/"+resultBean.getSumNumber()+"\t\t\t\t\t"+
                resultBean.getLocation());
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView tv_lecture_name;
        public TextView tv_lecture_date;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_lecture_name = (TextView) rootView.findViewById(R.id.tv_lecture_name);
            this.tv_lecture_date = (TextView) rootView.findViewById(R.id.tv_lecture_date);
        }

    }
}
