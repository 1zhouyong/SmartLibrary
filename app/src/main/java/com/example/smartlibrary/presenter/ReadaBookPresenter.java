package com.example.smartlibrary.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.view.ReadBookView;

/**
 * author: ${周勇}
 * Date: 2020/5/2
 * Description:{}
 */
public class ReadaBookPresenter extends BaseMvpPresenter<ReadBookView> {

    private Context context;

    public ReadaBookPresenter(Context context){
        this.context = context;
    }

    public void getDate(Intent intent){
        String bookName = intent.getStringExtra("bookName");
        String bookPic = intent.getStringExtra("bookUrl");
        mView.showMsg(bookName,bookPic);
    }
}
