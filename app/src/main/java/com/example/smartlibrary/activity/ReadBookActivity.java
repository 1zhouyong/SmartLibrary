package com.example.smartlibrary.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.smartlibrary.R;
import com.example.smartlibrary.base.BaseActivity;
import com.example.smartlibrary.presenter.ReadaBookPresenter;
import com.example.smartlibrary.util.GetTime;
import com.example.smartlibrary.view.ReadBookView;

public class ReadBookActivity extends BaseActivity<ReadBookView, ReadaBookPresenter> implements ReadBookView {


    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        initView();

        presenter.attach(this);
        presenter.getDate(getIntent());
    }

    @Override
    public ReadaBookPresenter initPresent() {
        return new ReadaBookPresenter(this);
    }

    @Override
    public void showMsg(String bookName, String BookUrl) {
        initToolbar(R.id.toolbar_activity, R.id.toolbar_text_activity, bookName);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(GetTime.changeImageUrl(BookUrl));
        System.out.println(GetTime.changeImageUrl(BookUrl));
    }


    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
    }
}
