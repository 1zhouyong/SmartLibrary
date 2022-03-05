package com.example.smartlibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartlibrary.CodeActivityActivity;
import com.example.smartlibrary.R;
import com.example.smartlibrary.adapter.MyBorrowAdapter;
import com.example.smartlibrary.base.BaseActivity;
import com.example.smartlibrary.bean.MyBorrowBean;
import com.example.smartlibrary.presenter.MyBorrowPresenter;
import com.example.smartlibrary.util.GetTime;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.MyBorrowView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyBorrowActivity extends BaseActivity<MyBorrowView, MyBorrowPresenter> implements MyBorrowView {

    private CircleImageView iv_circle;
    private TextView tv_name;
    private ListView lv_myBorrow;
    private MyBorrowAdapter adapter;
    private LinearLayout ll_noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_borrow);
        initView();
        Glide.with(this).load(GetTime.changeImageUrl(MainActivity.headUrl)).into(iv_circle);
        tv_name.setText(MainActivity.userName);
        presenter.getMyBorrow();
    }

    @Override
    public MyBorrowPresenter initPresent() {
        return new MyBorrowPresenter(this);
    }

    @Override
    public void showMyBorrow(final List<MyBorrowBean.ResultBean> resultBeans) {
        if (resultBeans.size() == 0){
            ll_noData.setVisibility(View.VISIBLE);
        }else {
            ll_noData.setVisibility(View.GONE);
            adapter = new MyBorrowAdapter(this, resultBeans, new MyBorrowAdapter.CodeCallBack() {
                @Override
                public void showCode(int potion) {
                    Intent intent = new Intent(MyBorrowActivity.this, CodeActivityActivity.class);
                    intent.putExtra("type", "我的借书");
                    intent.putExtra("name", resultBeans.get(potion).getName());
                    startActivity(intent);
                }
            }, new MyBorrowAdapter.ReturnCallBack() {
                @Override
                public void returnBook(int position) {
                    presenter.returnBook(resultBeans.get(position).getId());
                    resultBeans.remove(position);
                    adapter.notifyDataSetChanged();
                    if (resultBeans.size() == 0){
                        ll_noData.setVisibility(View.VISIBLE);
                    }

                }
            });
            lv_myBorrow.setAdapter(adapter);
        }
    }

    @Override
    public void showReturnmsg(String msg) {
        LoadDialog.showToast(MyBorrowActivity.this, msg);
    }

    private void initView() {
        initToolbar(R.id.toolbar_activity, R.id.toolbar_text_activity, "我的借书");
        iv_circle = (CircleImageView) findViewById(R.id.iv_circle);
        tv_name = (TextView) findViewById(R.id.tv_name);
        lv_myBorrow = (ListView) findViewById(R.id.lv_myBorrow);
        ll_noData = (LinearLayout) findViewById(R.id.ll_noData);
    }

}
