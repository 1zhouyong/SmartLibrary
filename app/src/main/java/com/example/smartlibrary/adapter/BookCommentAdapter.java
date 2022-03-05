package com.example.smartlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartlibrary.R;
import com.example.smartlibrary.bean.BookComment;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/5/1
 * Description:{}
 */
public class BookCommentAdapter extends BaseAdapter {

    private Context context;
    private List<BookComment.ResultBean> resultBeans;
    private dianzhanCallBack dianzhanCallBack;
    private ApplyCommentCallBack applyCommentCallBack;

    public BookCommentAdapter(Context context, List<BookComment.ResultBean> resultBeans,dianzhanCallBack dianzhanCallBack,
                              ApplyCommentCallBack applyCommentCallBack) {
        this.context = context;
        this.resultBeans = resultBeans;
        this.dianzhanCallBack = dianzhanCallBack;
        this.applyCommentCallBack = applyCommentCallBack;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_book_comment, viewGroup, false);
        }
        ViewHolder holder = new ViewHolder(view);
        holder.tv_comment.setText(resultBeans.get(i).getComment());
        holder.tv_comment_name.setText(resultBeans.get(i).getUserName());
        holder.tv_comment_time.setText(new SimpleDateFormat("yyyy年MM月dd日").format(resultBeans.get(i).getUpdatedAt()));
        if (resultBeans.get(i).isHasPraise()){
            holder.dianzhan.setImageResource(R.mipmap.dianzhan_press);
        }else {
            holder.dianzhan.setImageResource(R.mipmap.dianzhan_normal);
        }
        if (resultBeans.get(i).getBookCommentReciveDTOList()!=null){
            holder.comment.setText(""+resultBeans.get(i).getBookCommentReciveDTOList().size());
        }else {
            holder.comment.setText("0");
        }
        holder.tv_dianzhannum.setText(""+resultBeans.get(i).getCountPraise());
        holder.dianzhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dianzhanCallBack.clickDianzhan(i);
            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyCommentCallBack.clickComment(i);
            }
        });
        return view;
    }

    public static
    class ViewHolder {
        public View rootView;
        public TextView tv_comment_name;
        public TextView tv_comment;
        public TextView tv_comment_time;
        public TextView comment;
        public ImageView dianzhan;
        public TextView tv_dianzhannum;



        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_comment_name = (TextView) rootView.findViewById(R.id.tv_comment_name);
            this.tv_comment = (TextView) rootView.findViewById(R.id.tv_comment);
            this.tv_comment_time = (TextView) rootView.findViewById(R.id.tv_comment_time);
            this.comment = (TextView) rootView.findViewById(R.id.comment);
            this.tv_dianzhannum = (TextView) rootView.findViewById(R.id.tv_dianzhannum);

            this.dianzhan = (ImageView) rootView.findViewById(R.id.dianzhan);
        }

    }
    public interface dianzhanCallBack{
        void clickDianzhan(int position);
    }
    public interface ApplyCommentCallBack{
        void clickComment(int position);
    }
}
