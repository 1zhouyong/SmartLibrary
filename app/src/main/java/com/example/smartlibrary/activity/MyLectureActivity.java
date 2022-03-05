package com.example.smartlibrary.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.smartlibrary.CodeActivityActivity;
import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.R;
import com.example.smartlibrary.adapter.MyLectureAdapter;
import com.example.smartlibrary.base.BaseActivity;
import com.example.smartlibrary.bean.LectureListBean;
import com.example.smartlibrary.presenter.MyLecturePresenter;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.MyLectureView;

import java.util.ArrayList;
import java.util.List;

public class MyLectureActivity extends BaseActivity<MyLectureView, MyLecturePresenter> implements MyLectureView {


    private SwipeMenuListView lv_myLecture;
    private LinearLayout ll_noData;
    private List<LectureListBean.ResultBean> resultBeans = new ArrayList<>();
    private int pos;
    private MyLectureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lecture);
        initView();
        presenter.getMyLecture();
        final SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(InitApp.getInstance());
                deleteItem.setBackground(new ColorDrawable(Color.LTGRAY));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setTitle("签到");
                deleteItem.setTitleSize(20);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);

                SwipeMenuItem cancelItem = new SwipeMenuItem(InitApp.getInstance());
                cancelItem.setBackground(new ColorDrawable(Color.parseColor("#FFA500")));
                cancelItem.setWidth(dp2px(120));
                cancelItem.setTitle("取消预约");
                cancelItem.setTitleSize(20);
                cancelItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(cancelItem);

                SwipeMenuItem openItem = new SwipeMenuItem(InitApp.getInstance());
                openItem.setBackground(new ColorDrawable(Color.GREEN));
                openItem.setWidth(dp2px(90));
                openItem.setTitle("签退");
                openItem.setTitleSize(20);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);


            }
        };
        lv_myLecture.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                pos = position;
                switch (index){
                    case 0:
                        stopCode("讲座签到",resultBeans.get(position).getName());
                        break;
                    case 1:
                        presenter.cancelLecture(resultBeans.get(position).getId());
                        break;
                    case 2:
                        stopCode("讲座签退",resultBeans.get(position).getName());

                        break;
                }
                return true;
            }
        });
        lv_myLecture.setMenuCreator(swipeMenuCreator);


    }

    private void stopCode(String type, String name) {
        Intent intent = new Intent(this, CodeActivityActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("name",name);
        startActivity(intent);
    }



    @Override
    public MyLecturePresenter initPresent() {
        return new MyLecturePresenter(this);
    }

    public int dp2px(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private void initView() {
        initToolbar(R.id.toolbar_activity, R.id.toolbar_text_activity, "我的预约");
        lv_myLecture = (SwipeMenuListView) findViewById(R.id.lv_myLecture);
        ll_noData = (LinearLayout) findViewById(R.id.ll_noData);
        lv_myLecture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("lectureList",resultBeans.get(i));
                Intent intent = new Intent(InitApp.getInstance(), BookLectureActivity.class);
                intent.putExtras(bundle);
                intent.putExtra("flag","yes");
                startActivity(intent);
            }
        });
    }

    @Override
    public void showMyLecture(List<LectureListBean.ResultBean> list) {
        if (list.size() == 0) {
            ll_noData.setVisibility(View.VISIBLE);
        } else {
            resultBeans.addAll(list);
            adapter = new MyLectureAdapter(this, resultBeans);
            lv_myLecture.setAdapter(adapter);
        }

    }

    @Override
    public void showMsg(String text) {
        LoadDialog.showToast(this,text);
        resultBeans.remove(pos);
        adapter.notifyDataSetChanged();
        if (resultBeans.size() == 0){
            ll_noData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null){
            adapter.notifyDataSetChanged();
        }
    }
}
