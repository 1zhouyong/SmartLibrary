package com.example.smartlibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.DrmInitData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.smartlibrary.activity.MainActivity;
import com.example.smartlibrary.util.CodeUtils;
import com.example.smartlibrary.util.GetTime;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.util.PicUrlToBitmap;
import com.google.gson.JsonObject;
import com.google.zxing.WriterException;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class CodeActivityActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ib_back;
    private TextView tv_title;
    private CircleImageView iv_head;
    private TextView tv_type;
    private ImageView ib_code;
    private TextView tv_username;
    private Thread thread;
    private String type;
    private String name;
    private Bitmap bitmap;
    private Thread thread1;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_activity);
        initView();
        initData();
    }

    private void initData() {
        type = getIntent().getStringExtra("type");
        name = getIntent().getStringExtra("name");
        tv_title.setText(type);
        tv_type.setText(name);
        Glide.with(this).load(GetTime.changeImageUrl(MainActivity.headUrl)).into(iv_head);
        tv_username.setText(MainActivity.userName);

        initCode();
    }

    private void initCode() {

        HashMap map = new HashMap<>();
        map.put(type, name);
        map.put("用户账号", MainActivity.studentNumber);
        msg = new JSONObject(map).toString();
        returnBitMap(GetTime.changeImageUrl(MainActivity.headUrl));
    }

    private void initView() {
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_head = (CircleImageView) findViewById(R.id.iv_head);
        tv_type = (TextView) findViewById(R.id.tv_type);
        ib_code = (ImageView) findViewById(R.id.ib_code);

        ib_back.setOnClickListener(this);
        tv_username = (TextView) findViewById(R.id.tv_username);
        thread = new Thread() {
            @Override
            public void run() {
                Glide.get(CodeActivityActivity.this).clearDiskCache();
                super.run();
            }
        };
        thread.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (thread != null) {
            thread.interrupt();
        }
        if (thread1 != null) {
            thread1.interrupt();
        }
    }

    public void returnBitMap(final String url) {
        LoadDialog.showDialog(this);
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    try {
                        final Bitmap code = CodeUtils.createCode(InitApp.getInstance(), msg, bitmap);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                LoadDialog.disDialog();
                                ib_code.setImageBitmap(code);
                            }
                        });
                    } catch (WriterException e) {
                        LoadDialog.disDialog();
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    LoadDialog.disDialog();
                    e.printStackTrace();
                }
            }

        });
        thread1.start();
    }
}
