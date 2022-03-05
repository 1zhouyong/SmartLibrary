package com.example.smartlibrary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.smartlibrary.activity.LoginActivity;

import java.util.ArrayList;

public class SpainshActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager vb_spanish;
    private RadioButton rb_1;
    private RadioButton rb_02;
    private RadioButton rb_03;
    private ArrayList<ImageView> imageViews;
    private ImageView imageView;
    private Button btn_start;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spainsh);
        initView();
        SharedPreferences enter = getSharedPreferences("enter", MODE_PRIVATE);
        if (enter.getBoolean("flag",false)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        int ids[] = new int[]{
                R.drawable.spanish01,
                R.drawable.spanish02,
                R.drawable.spanish03
        };
        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);
            imageViews.add(imageView);
        }
        vb_spanish.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViews.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView iv = imageViews.get(position);
                container.addView(iv);
                return iv;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }


            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        vb_spanish.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    rb_1.setBackgroundResource(R.drawable.circle_black);
                    rb_02.setBackgroundResource(R.drawable.circle_white);
                    rb_03.setBackgroundResource(R.drawable.circle_white);

                } else if (i == 1) {
                    rb_1.setBackgroundResource(R.drawable.circle_white);
                    rb_02.setBackgroundResource(R.drawable.circle_black);
                    rb_03.setBackgroundResource(R.drawable.circle_white);

                } else {
                    rb_1.setBackgroundResource(R.drawable.circle_white);
                    rb_02.setBackgroundResource(R.drawable.circle_white);
                    rb_03.setBackgroundResource(R.drawable.circle_black);
                    btn_start.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    private void initView() {
        vb_spanish = (ViewPager) findViewById(R.id.vb_spanish);
        rb_1 = (RadioButton) findViewById(R.id.rb_1);
        rb_02 = (RadioButton) findViewById(R.id.rb_02);
        rb_03 = (RadioButton) findViewById(R.id.rb_03);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                SharedPreferences.Editor edit = getSharedPreferences("enter",MODE_PRIVATE).edit();
                edit.putBoolean("flag",true);
                edit.apply();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

}
