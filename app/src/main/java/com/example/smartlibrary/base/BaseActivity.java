package com.example.smartlibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.R;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;

/**
 * author: ${周勇}
 * Date: 2020/4/3
 * Description:{}
 */
public abstract class BaseActivity<V,T extends BaseMvpPresenter<V>> extends AppCompatActivity  {

    public T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitApp.getInstance().addActitivity(this);

        presenter = initPresent();
    }

    public Toolbar initToolbar(int id, int titleId, String titleString) {
        Toolbar toolbar = (Toolbar) findViewById(id);
        TextView textView = (TextView) findViewById(titleId);
        textView.setText(titleString);
        toolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }



    public abstract T initPresent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        InitApp.getInstance().removeActivity(this);
        presenter.deattach();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
