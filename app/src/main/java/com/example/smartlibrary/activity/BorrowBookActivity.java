package com.example.smartlibrary.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.example.smartlibrary.R;
import com.example.smartlibrary.base.BaseActivity;
import com.example.smartlibrary.presenter.BorrowBookPresenter;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.BorrowBookView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BorrowBookActivity extends BaseActivity<BorrowBookView, BorrowBookPresenter> implements BorrowBookView, View.OnClickListener {

    private TextView tv_student_number;
    private TextView tv_student_name;
    private TextView tv_book_name;
    private TextView tv_book_startTime;
    private TextView tv_book_stopTime;
    private Button submit_seat;
    private String bookName;
    private String bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);
        initView();
        bookName = getIntent().getStringExtra("bookName");
        bookId = getIntent().getStringExtra("bookId");
        tv_book_name.setText(bookName);
        tv_student_name.setText(MainActivity.userName);
        tv_student_number.setText(MainActivity.studentNumber);
    }

    @Override
    public BorrowBookPresenter initPresent() {
        return new BorrowBookPresenter(this);
    }

    private void initView() {
        tv_student_number = (TextView) findViewById(R.id.tv_student_number);
        tv_student_name = (TextView) findViewById(R.id.tv_student_name);
        tv_book_name = (TextView) findViewById(R.id.tv_book_name);
        tv_book_startTime = (TextView) findViewById(R.id.tv_book_startTime);
        tv_book_stopTime = (TextView) findViewById(R.id.tv_book_stopTime);
        submit_seat = (Button) findViewById(R.id.submit_seat);
        tv_book_startTime.setOnClickListener(this);
        tv_book_stopTime.setOnClickListener(this);
        submit_seat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_seat:
                try {
                    Date s1 = new SimpleDateFormat("yyyy-MM-dd").parse(tv_book_startTime.getText().toString().trim());
                    Date s2 = new SimpleDateFormat("yyyy-MM-dd").parse(tv_book_stopTime.getText().toString().trim());
                    if (s1.getTime()>=s2.getTime()){
                        LoadDialog.showToast(this,"请输入合适时间");
                    }else if (tv_book_startTime.getText().toString()==null||
                    tv_book_stopTime.getText().toString().trim()==null){
                        LoadDialog.showToast(this,"请输入日期");
                    }else {
                        presenter.borrowBook(bookId,tv_book_startTime.getText().toString().trim(),
                                tv_book_stopTime.getText().toString().trim());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                    break;
            case R.id.tv_book_startTime:
                initBirth(1);
                break;
            case R.id.tv_book_stopTime:
                initBirth(2);
                break;
        }
    }

    private void initBirth(final int i) {
        TimePickerBuilder builder = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (i == 1) {
                    tv_book_startTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
                } else {
                    tv_book_stopTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
                }
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .isCenterLabel(false);
        builder.build().show();
    }

    @Override
    public void showBorrowBookMsg(String msg) {
        LoadDialog.showToast(this, msg);
        if (msg.equals("借书成功，请前往我的借书查看")) {
            finish();
        }
    }
}
