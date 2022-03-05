package com.example.smartlibrary.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.smartlibrary.OwlView;
import com.example.smartlibrary.R;
import com.example.smartlibrary.base.BaseActivity;
import com.example.smartlibrary.presenter.LoginPresenter;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.LoginView;


public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    private OwlView mOwlView;
    private EditText email, et_password;
    private Button login;
    private CheckBox cb_remember_password;
    private Button btn_login;
    private LinearLayout ll_login_pane;
    private OwlView owl_view;
    public static String loginToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        mOwlView = (OwlView) findViewById(R.id.owl_view);
        email = (EditText) findViewById(R.id.email);
        et_password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btn_login);
        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mOwlView.open();

                } else {
                    mOwlView.close();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                presenter.getData(cb_remember_password.isChecked(), account, password);
            }
        });

        rememeberPassword();
    }

    @Override
    public LoginPresenter initPresent() {
        return new LoginPresenter(this);
    }


    @Override
    public void showMessage(boolean msg) {
        if (msg == true) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            LoadDialog.showToast(this, "账号或密码错误");
        }
    }

    @Override
    public void showToken(String token) {
        loginToken = token;
    }


    public void rememeberPassword() {
        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        String username = sp.getString("username", "");
        if (!username.equals("") || username != null) {
            email.setText(username);
            email.setSelection(username.length());
            et_password.setText(sp.getString("password", ""));
        }
    }

    @Override
    public void showLoad() {
        LoadDialog.showDialog(this);

    }

    @Override
    public void hideLoad() {
        LoadDialog.disDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deattach();
    }

    private void initView() {
        cb_remember_password = (CheckBox) findViewById(R.id.cb_remember_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        ll_login_pane = (LinearLayout) findViewById(R.id.ll_login_pane);
        owl_view = (OwlView) findViewById(R.id.owl_view);

    }

}
