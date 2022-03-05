package com.example.smartlibrary.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.smartlibrary.base.BaseActivity;
import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.R;
import com.example.smartlibrary.adapter.Grid_Seat_Adapter;
import com.example.smartlibrary.bean.SeatStatusBean;
import com.example.smartlibrary.presenter.BookSeatPresenter;
import com.example.smartlibrary.util.GetTime;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.BookSeatView;

import java.util.ArrayList;
import java.util.List;

public class Book_Seat_Activity extends BaseActivity<BookSeatView, BookSeatPresenter> implements
        BookSeatView, View.OnClickListener {

    private TextView toolbar_text_activity;
    private Toolbar toolbar_activity;
    private Spinner spinner_choose_room;
    private GridView gridView_seat;
    private List<String> seatStatusList;
    private Button btn_book;
    private int seatId;
    private int date;
    private String seat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__seat_);
        initView();
        initSpinner();
        date = Integer.parseInt(getIntent().getStringExtra("date"));
        presenter.getSeatStatus(GetTime.getNowDay1(date), getResources().getStringArray(R.array.classroom)[0]);
        spinner_choose_room.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.getSeatStatus(GetTime.getNowDay1(date), getResources().getStringArray(R.array.classroom)[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initSpinner() {
        String[] classroom = getResources().getStringArray(R.array.classroom);
        //构造ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.simple_spinner_item, classroom);
        //设置下拉样式以后显示的样式
        adapter.setDropDownViewResource(R.layout.my_drop_down_item);
        spinner_choose_room.setAdapter(adapter);

    }

    @Override
    public BookSeatPresenter initPresent() {
        return new BookSeatPresenter(this);
    }




    private void initView() {
        initToolbar(R.id.toolbar_activity, R.id.toolbar_text_activity, "实况预约");
        toolbar_text_activity = (TextView) findViewById(R.id.toolbar_text_activity);
        toolbar_activity = (Toolbar) findViewById(R.id.toolbar_activity);

        spinner_choose_room = (Spinner) findViewById(R.id.spinner_choose_room);

        gridView_seat = (GridView) findViewById(R.id.gridView_seat);
        btn_book = (Button) findViewById(R.id.btn_book);
        btn_book.setOnClickListener(this);

    }

    @Override
    public void showSeatStatus(final SeatStatusBean seatStatusBean) {
        seatStatusList = new ArrayList<>();
        for (int i = 0; i < seatStatusBean.getResult().size(); i++) {
            seatStatusList.add(seatStatusBean.getResult().get(i).getStatus());
        }
        final Grid_Seat_Adapter adapter = new Grid_Seat_Adapter(InitApp.getInstance(), seatStatusList);
        gridView_seat.setAdapter(adapter);
        gridView_seat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (seatStatusList.get(i).equals("0")) {
                    btn_book.setEnabled(true);
                    seatStatusList.clear();
                    for (int i1 = 0; i1 < seatStatusBean.getResult().size(); i1++) {
                        seatStatusList.add(seatStatusBean.getResult().get(i1).getStatus());
                    }
                    seatStatusList.set(i,"1");
                    adapter.notifyDataSetChanged();
                    seatId = seatStatusBean.getResult().get(i).getId();
                    seat = seatStatusBean.getResult().get(i).getNumber();

                } else {
                    LoadDialog.showToast(InitApp.getInstance(), "该座位已经被预定");
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_book:
                Intent intent = new Intent(this, Submit_Seat_Activity.class);
                intent.putExtra("seatId",String.valueOf(seatId));
                intent.putExtra("date",String.valueOf(date));
                intent.putExtra("seat",seat);
                intent.putExtra("classroom",(String) spinner_choose_room.getSelectedItem());
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getSeatStatus(GetTime.getNowDay1(date), getResources().getStringArray(R.array.classroom)[0]);
    }
}
