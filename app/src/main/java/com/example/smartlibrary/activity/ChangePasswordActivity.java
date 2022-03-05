package com.example.smartlibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.R;
import com.example.smartlibrary.base.BaseActivity;
import com.example.smartlibrary.presenter.ChangePasswordPresenter;
import com.example.smartlibrary.util.GetTime;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.ChangePasswordView;

public class ChangePasswordActivity extends BaseActivity<ChangePasswordView, ChangePasswordPresenter> implements ChangePasswordView, View.OnClickListener {


    private TextView tv_username;
    private EditText edt_newPassword;
    private EditText edt_confirmPassword;
    private CheckBox cb_showPassword;
    private Button btn_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
        tv_username.setText(MainActivity.studentNumber);
        cb_showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cb_showPassword.isChecked()){
                    edt_newPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                    edt_confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT);

                }else {
                    edt_newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edt_confirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });
    }

    @Override
    public ChangePasswordPresenter initPresent() {
        return new ChangePasswordPresenter(this);
    }

    @Override
    public void showMsg(String msg) {
        LoadDialog.showToast(this,msg);
        if (msg.equals("修改成功")){
           SharedPreferences.Editor sp = getSharedPreferences("login", Context.MODE_PRIVATE).edit();
            sp.putString("password", "");
            sp.apply();
            MainActivity.finishActivity();
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
    }

    private void initView() {
        initToolbar(R.id.toolbar_activity, R.id.toolbar_text_activity, "修改密码");
        tv_username = (TextView) findViewById(R.id.tv_username);
        edt_newPassword = (EditText) findViewById(R.id.edt_newPassword);
        edt_confirmPassword = (EditText) findViewById(R.id.edt_confirmPassword);
        cb_showPassword = (CheckBox) findViewById(R.id.cb_showPassword);
        btn_change = (Button) findViewById(R.id.btn_change);
        btn_change.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String newPassword = edt_newPassword.getText().toString().trim();
        if (TextUtils.isEmpty(newPassword)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String confirmPassword = edt_confirmPassword.getText().toString().trim();
        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
            return;
        }


        // TODO validate success, do something

        if (newPassword.equals(confirmPassword)){
            presenter.changePassword(newPassword);
        }else {
            LoadDialog.showToast(this,"密码不一致");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change:
                submit();
                break;
        }
    }
}
