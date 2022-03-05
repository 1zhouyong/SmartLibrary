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
import com.example.smartlibrary.bean.LibraryTypeBean;
import com.example.smartlibrary.util.GetTime;

import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/4/26
 * Description:{}
 */
public class LibraryTypeAdapter extends BaseAdapter {

    private Context context;
    private List<LibraryTypeBean.ResultBean> resultBeans;

    public LibraryTypeAdapter(Context context, List<LibraryTypeBean.ResultBean> resultBeans) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_library_type, viewGroup, false);
        }
        ViewHolder holder = new ViewHolder(view);
        Glide.with(context).load(GetTime.changeImageUrl(resultBeans.get(i).getPicPath())).into(holder.iv_book_pic);
        holder.tv_book_name.setText(resultBeans.get(i).getName());
        return view;
    }

    public static
    class ViewHolder {
        public View rootView;
        public ImageView iv_book_pic;
        public TextView tv_book_name;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_book_pic = (ImageView) rootView.findViewById(R.id.iv_book_pic);
            this.tv_book_name = (TextView) rootView.findViewById(R.id.tv_book_name);
        }

    }
}
