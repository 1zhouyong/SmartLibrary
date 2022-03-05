package com.example.smartlibrary.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.GridView;
import android.widget.TextView;

import com.example.smartlibrary.R;
import com.example.smartlibrary.adapter.MyBookAdapter;
import com.example.smartlibrary.base.BaseActivity;
import com.example.smartlibrary.bean.MyBookBean;
import com.example.smartlibrary.presenter.MyBookPresenter;
import com.example.smartlibrary.view.MyBookView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.util.List;

public class MyBookActivity extends BaseActivity<MyBookView, MyBookPresenter> implements MyBookView {


    private GridView gridView_mybook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book);
        initView();
        presenter.getMyBookNews();
    }

    @Override
    public MyBookPresenter initPresent() {
        return new MyBookPresenter(this);
    }

    @Override
    public void showMyBook(List<MyBookBean.ResultBean> resultBean) {
        MyBookAdapter adapter = new MyBookAdapter(this, resultBean);
        gridView_mybook.setAdapter(adapter);


    }

    private void initView() {
        initToolbar(R.id.toolbar_activity,R.id.toolbar_text_activity,"我的书架");
        gridView_mybook = (GridView) findViewById(R.id.gridView_mybook);
    }



}
