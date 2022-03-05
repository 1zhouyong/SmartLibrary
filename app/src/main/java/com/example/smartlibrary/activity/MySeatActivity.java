package com.example.smartlibrary.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartlibrary.R;
import com.example.smartlibrary.adapter.SeatInfomationAdpater;
import com.example.smartlibrary.base.BaseActivity;
import com.example.smartlibrary.bean.SeatInfomationBean;
import com.example.smartlibrary.presenter.MySeatPresenter;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.MySeatView;

import java.util.List;

public class MySeatActivity extends BaseActivity<MySeatView, MySeatPresenter> implements MySeatView {

    private TextView toolbar_title;
    private Toolbar toolbar_activity;
    private ListView list_seat_information;
    private SeatInfomationAdpater adapter;
    private LinearLayout ll_nodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_seat);
        initView();
        presenter.getSeatInfo(-1);
    }

    @Override
    public MySeatPresenter initPresent() {
        return new MySeatPresenter(this);
    }

    private void initView() {
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_activity = (Toolbar) findViewById(R.id.toolbar_activity);
        toolbar_activity.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(toolbar_activity);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        list_seat_information = (ListView) findViewById(R.id.list_seat_information);
        ll_nodata = (LinearLayout) findViewById(R.id.ll_nodata);
    }


    @Override
    public void showSeatInfo(final List<SeatInfomationBean> seatInfomationBeanList) {
        if (seatInfomationBeanList.size() == 0){
            ll_nodata.setVisibility(View.VISIBLE);
        }else {
            ll_nodata.setVisibility(View.GONE);
            adapter = new SeatInfomationAdpater(this, seatInfomationBeanList, new SeatInfomationAdpater.CancelCallBack() {
                @Override
                public void cancelSite(int position) {
                    SeatInfomationBean.ResultBean result = seatInfomationBeanList.get(position).getResult();
                    presenter.cancelSite(result.getId(), (String) result.getDate());
                    seatInfomationBeanList.remove(position);
                    if (seatInfomationBeanList.size() == 0){
                        ll_nodata.setVisibility(View.VISIBLE);
                    }

                }
            });
            list_seat_information.setAdapter(adapter);
        }
    }

    @Override
    public void cancelSuccess() {
        LoadDialog.showToast(this,"取消预约成功");
        adapter.notifyDataSetChanged();
    }
}
