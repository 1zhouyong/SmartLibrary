package com.example.smartlibrary.activity;

import android.content.Intent;
import android.icu.text.MessagePattern;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartlibrary.R;

public class UpdateInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ib_back;
    private TextView toolbar_text;
    private Button btn_save;
    private EditText edit_text;
    private ImageButton ib_clear;
    private String toolbarName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        initView();
        initData();

    }

    private void initData() {
        toolbarName = getIntent().getStringExtra("toolbarName");
        String type = getIntent().getStringExtra("type");
        if (toolbarName.equals("修改手机号码")){
            edit_text.setInputType(InputType.TYPE_CLASS_PHONE);
        }
        toolbar_text.setText(toolbarName);
        edit_text.setText(type);
        edit_text.setSelection(type.length());//将光标移至文字末尾

    }

    private void initView() {
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        toolbar_text = (TextView) findViewById(R.id.toolbar_text);
        btn_save = (Button) findViewById(R.id.btn_save);

        ib_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        edit_text = (EditText) findViewById(R.id.edit_text);
        edit_text.setOnClickListener(this);
        ib_clear = (ImageButton) findViewById(R.id.ib_clear);
        ib_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.btn_save:
                submit();
                break;
            case R.id.ib_clear:
                edit_text.setText("");
                break;
        }
    }

    private void submit() {
        // validate
        String text = edit_text.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "text不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (toolbarName.equals("修改手机号码")){
            if (judPhone(text)){
                Intent intent = new Intent(this, MyInfoActivity.class);
                intent.putExtra("newData",text);
                setResult(RESULT_OK,intent);
                finish();
            }

        }else {
            // TODO validate success, do something
            Intent intent = new Intent(this, MyInfoActivity.class);
            intent.putExtra("newData",text);
            setResult(RESULT_OK,intent);
            finish();
        }


    }
    //验证手机号
    private boolean judPhone(String text) {
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "请输入您的电话号码", Toast.LENGTH_LONG).show();
            edit_text.requestFocus();
            return false;
        } else if (text.length() != 11) {
            Toast.makeText(this, "您的电话号码位数不正确", Toast.LENGTH_LONG).show();
            edit_text.requestFocus();
            return false;
        } else {
            String num = "[1][358]\\d{9}";
            if (text.matches(num))
                return true;
            else {
                Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }

}
