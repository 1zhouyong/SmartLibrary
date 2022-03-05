package com.example.smartlibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartlibrary.base.BaseActivity;
import com.example.smartlibrary.R;
import com.example.smartlibrary.presenter.Submit_Seat_Presenter;
import com.example.smartlibrary.util.GetTime;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.Submit_Seat_View;

public class Submit_Seat_Activity extends BaseActivity<Submit_Seat_View, Submit_Seat_Presenter> implements Submit_Seat_View, View.OnClickListener {


    private TextView toolbar_text_activity;
    private Toolbar toolbar_activity;
    private TextView tv_student_number;
    private TextView tv_name;
    private TextView tv_classroom;
    private TextView tv_seat;
    private TextView tv_date;
    private Button submit_seat;
    private String seatId;
    private String date;
    private String seat;
    private String classroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_book);
        initView();

    }

    @Override
    public Submit_Seat_Presenter initPresent() {
        return new Submit_Seat_Presenter(this);
    }

    private void initView() {
        initToolbar(R.id.toolbar_activity, R.id.toolbar_text_activity, "确认信息");
        tv_student_number = (TextView) findViewById(R.id.tv_student_number);
        tv_student_number.setOnClickListener(this);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_name.setOnClickListener(this);
        tv_classroom = (TextView) findViewById(R.id.tv_classroom);
        tv_classroom.setOnClickListener(this);
        tv_seat = (TextView) findViewById(R.id.tv_seat);
        tv_seat.setOnClickListener(this);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_date.setOnClickListener(this);
        submit_seat = (Button) findViewById(R.id.submit_seat);
        submit_seat.setOnClickListener(this);
        Intent intent = getIntent();
        seatId = intent.getStringExtra("seatId");
        date = intent.getStringExtra("date");
        seat = intent.getStringExtra("seat");
        classroom = intent.getStringExtra("classroom");

        tv_student_number.setText(MainActivity.studentNumber);
        tv_name.setText(MainActivity.userName);
        tv_classroom.setText(classroom);
        tv_seat.setText(seat);
        tv_date.setText(GetTime.getNowDay(Integer.parseInt(date)));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_seat:
                presenter.submit_Seat(MainActivity.userId,seatId,GetTime.getNowDay1(Integer.parseInt(date)));
                break;
        }
    }

    @Override
    public void submitSuccess() {
        LoadDialog.showToast(this,"预约成功，你可以前往我的预约查看");
        finish();
    }
}
