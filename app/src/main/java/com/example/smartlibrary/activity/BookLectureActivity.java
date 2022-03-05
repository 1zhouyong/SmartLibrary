package com.example.smartlibrary.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartlibrary.R;
import com.example.smartlibrary.base.BaseActivity;
import com.example.smartlibrary.bean.LectureListBean;
import com.example.smartlibrary.presenter.BookLecturePresenter;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.BookLectureView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookLectureActivity extends BaseActivity<BookLectureView, BookLecturePresenter> implements BookLectureView, View.OnClickListener {


    private TextView tv_lecture_name;
    private TextView tv_lecture_location;
    private TextView tv_lecture_people;
    private TextView tv_lecture_teacher;
    private TextView tv_desc;
    private TextView tv_startTime;
    private TextView tv_stopTime;
    private Button btn_bookLecture;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_info);
        initView();
        presenter.attach(this);
        if (getIntent().getStringExtra("flag").equals("yes")){
            btn_bookLecture.setVisibility(View.GONE);
        }
        presenter.getResultBean(getIntent().getExtras());
    }

    @Override
    public BookLecturePresenter initPresent() {
        return new BookLecturePresenter(this);
    }

    @Override
    public void showResultBean(LectureListBean.ResultBean resultBean) {
        tv_lecture_name.setText(resultBean.getName());
        tv_lecture_location.setText(resultBean.getLocation());
        tv_lecture_teacher.setText(resultBean.getTeacher());
        tv_lecture_people.setText(resultBean.getOrderNumber()+"/"+resultBean.getSumNumber());
        tv_startTime.setText(getDate(resultBean.getBeginTime()));
        tv_stopTime.setText(getDate(resultBean.getEndTime()));
        id = resultBean.getId();
    }

    @Override
    public void showMessage(String msg) {
        LoadDialog.showToast(this,msg);
        finish();
    }


    private void initView() {
        initToolbar(R.id.toolbar_activity,R.id.toolbar_text_activity,"预约讲座");
        tv_lecture_name = (TextView) findViewById(R.id.tv_lecture_name);
        tv_lecture_location = (TextView) findViewById(R.id.tv_lecture_location);
        tv_lecture_people = (TextView) findViewById(R.id.tv_lecture_people);
        tv_lecture_teacher = (TextView) findViewById(R.id.tv_lecture_teacher);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        tv_startTime = (TextView) findViewById(R.id.tv_startTime);
        tv_stopTime = (TextView) findViewById(R.id.tv_stopTime);
        btn_bookLecture = (Button) findViewById(R.id.btn_bookLecture);

        btn_bookLecture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bookLecture:
                presenter.bookLecture(id);
                break;
        }
    }
    public String getDate(Date date){
        String format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss").format(date);
        return format;
    }
}
