package com.example.smartlibrary.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.R;
import com.example.smartlibrary.adapter.BookCommentAdapter;
import com.example.smartlibrary.base.BaseActivity;
import com.example.smartlibrary.bean.BookComment;
import com.example.smartlibrary.bean.LibraryTypeBean;
import com.example.smartlibrary.presenter.BookInfoPresenter;
import com.example.smartlibrary.util.GetTime;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.BookInfoView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BookInfoActivity extends BaseActivity<BookInfoView, BookInfoPresenter> implements BookInfoView, View.OnClickListener {

    private TextView tv_book_name;
    private TextView tv_book_author;
    private TextView tv_book_publish;
    private TextView tv_book_publish_time;
    private ImageView iv_boo_pic;
    private TextView tv_book_desc;
    private Button btn_add_book;
    private int bookId;

    private PopupWindow popupWindow;
    private View popupView = null;
    private EditText inputComment;
    private String nInputContentText;
    private TextView btn_submit;
    private RelativeLayout rl_input_container;
    private InputMethodManager mInputManager;
    private TextView book_review;
    private TextView white_booreview;
    private Timer timer;
    private ListView lv_bookreview;
    private LinearLayout ll_noData;
    List<BookComment.ResultBean> list = new ArrayList<>();
    private BookCommentAdapter adapter;
    private int bookid;
    private int parentId;
    private Button btn_borrow_book;
    private LibraryTypeBean.ResultBean book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        presenter.attach(this);
        initView();
        presenter.getBookInfo(getIntent().getExtras());

    }

    @Override
    public BookInfoPresenter initPresent() {
        return new BookInfoPresenter(this);
    }

    private void initView() {
        initToolbar(R.id.toolbar_activity, R.id.toolbar_text_activity, "书籍详情");
        tv_book_name = (TextView) findViewById(R.id.tv_book_name);
        tv_book_author = (TextView) findViewById(R.id.tv_book_author);
        tv_book_publish = (TextView) findViewById(R.id.tv_book_publish);
        tv_book_publish_time = (TextView) findViewById(R.id.tv_book_publish_time);
        iv_boo_pic = (ImageView) findViewById(R.id.iv_boo_pic);
        tv_book_desc = (TextView) findViewById(R.id.tv_book_desc);
        btn_add_book = (Button) findViewById(R.id.btn_add_book);
        btn_add_book.setOnClickListener(this);
        book_review = (TextView) findViewById(R.id.book_review);
        book_review.setOnClickListener(this);
        white_booreview = (TextView) findViewById(R.id.white_booreview);
        white_booreview.setOnClickListener(this);
        lv_bookreview = (ListView) findViewById(R.id.lv_bookreview);
        ll_noData = (LinearLayout) findViewById(R.id.ll_noData);
        ll_noData.setOnClickListener(this);
        btn_borrow_book = (Button) findViewById(R.id.btn_borrow_book);
        btn_borrow_book.setOnClickListener(this);
    }

    @Override
    public void showBookInfo(LibraryTypeBean.ResultBean resultBean) {
        book = resultBean;
        bookid = resultBean.getId();
        presenter.getBookComment(resultBean.getId());
        bookId = resultBean.getId();
        tv_book_name.setText(resultBean.getName());
        tv_book_author.setText("作者：" + resultBean.getAuthor());
        tv_book_publish.setText("出版社：" + resultBean.getPublishAddress());
        tv_book_publish_time.setText("出版时间：" + new SimpleDateFormat("yyyy年MM月dd天").format(resultBean.getPublishTime()));
        Glide.with(this).load(GetTime.changeImageUrl(resultBean.getPicPath())).into(iv_boo_pic);
        tv_book_desc.setText("\t\t\t" + "全书系统全面、循序渐进地介绍了Android软件开发的必备知识、经验和技巧。基于Android 7.0对第1版进行了全面更新，将所有知识点都在最新的Android系统上进行了重新适配，使用全新的Android Studio开发工具代替之前的Eclipse，并添加了对Material Design、运行时权限、多窗口模式、Gradle、RecyclerView、百分比布局、OkHttp、Lambda表达式等全新知识点的详细讲解。 本书内容通俗易懂，由浅入深，既是Android初学者的入门必备，也是Android开发者的进阶首选。");
    }

    @Override
    public void addBookMsg(String msg) {
        LoadDialog.showToast(InitApp.getInstance(), msg);
    }

    @Override
    public void showBookComment(final List<BookComment.ResultBean> resultBeans) {

        adapter = new BookCommentAdapter(this, resultBeans, new BookCommentAdapter.dianzhanCallBack() {
            @Override
            public void clickDianzhan(int position) {
                presenter.DianZhan(resultBeans.get(position).getId());
                if (resultBeans.get(position).isHasPraise()) {
                    resultBeans.get(position).setHasPraise(false);
                    resultBeans.get(position).setCountPraise(resultBeans.get(position).getCountPraise() - 1);
                } else {
                    resultBeans.get(position).setHasPraise(true);
                    resultBeans.get(position).setCountPraise(resultBeans.get(position).getCountPraise() + 1);

                }
                adapter.notifyDataSetChanged();
            }
        }, new BookCommentAdapter.ApplyCommentCallBack() {
            @Override
            public void clickComment(int position) {
                parentId = resultBeans.get(position).getId();
                showPopupcomment(2);
            }
        });
        lv_bookreview.setAdapter(adapter);
        book_review.setText("书评(" + resultBeans.size() + "条)");
    }

    @Override
    public void noData() {
        book_review.setText("书评(0条)");
    }

    @Override
    public void dianzhanSucceed() {
        presenter.getBookComment(bookId);
    }

    @Override
    public void applyComment() {
        presenter.getBookComment(bookId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_book:
                System.out.println(bookId);
                presenter.addBook(bookId);
                break;
            case R.id.white_booreview:
                showPopupcomment(1);
                break;
            case R.id.btn_borrow_book:
                Intent intent = new Intent(this, BorrowBookActivity.class);
                intent.putExtra("bookName",book.getName());
                intent.putExtra("bookId",String.valueOf(bookid));
                startActivity(intent);
                break;
        }
    }

    @SuppressLint("WrongConstant")
    private void showPopupcomment(final int flag) {
        if (popupView == null) {
//加载评论框的资源文件
            popupView = LayoutInflater.from(this).inflate(R.layout.comment_popupwindow, null);
        }
        inputComment = (EditText) popupView.findViewById(R.id.et_discuss);
        btn_submit = (Button) popupView.findViewById(R.id.btn_confirm);
        rl_input_container = (RelativeLayout) popupView.findViewById(R.id.rl_input_container);

//利用Timer这个Api设置延迟显示软键盘，这里时间为200毫秒
        timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputManager.showSoftInput(inputComment, 0);
            }

        }, 200);
        if (popupWindow == null) {
            popupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT, false);

        }
//popupWindow的常规设置，设置点击外部事件，背景色
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
                    popupWindow.dismiss();
                return false;

            }
        });

// 设置弹出窗体需要软键盘，放在setSoftInputMode之前
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
// 再设置模式，和Activity的一样，覆盖，调整大小。
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//设置popupwindow的显示位置，这里应该是显示在底部，即Bottom
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);

        popupWindow.update();

//设置监听
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            public void onDismiss() {

                mInputManager.hideSoftInputFromWindow(inputComment.getWindowToken(), 0); //强制隐藏键盘


            }
        });
//外部点击事件
        rl_input_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mInputManager.hideSoftInputFromWindow(inputComment.getWindowToken(), 0); //强制隐藏键盘
                popupWindow.dismiss();

            }
        });
//评论框内的发送按钮设置点击事件
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nInputContentText = inputComment.getText().toString().trim();

                if (nInputContentText == null || "".equals(nInputContentText)) {
                    LoadDialog.showToast(BookInfoActivity.this, "请输入评论内容");
                } else {
                    if (flag == 1) {
                        presenter.addComment(nInputContentText);
                    } else if (flag == 2) {
                        presenter.ReplyComment(nInputContentText, parentId);
                    }
                    mInputManager.hideSoftInputFromWindow(inputComment.getWindowToken(), 0);
                    popupWindow.dismiss();
                    return;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}

