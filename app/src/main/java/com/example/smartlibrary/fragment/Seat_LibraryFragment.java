package com.example.smartlibrary.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartlibrary.base.BaseFragment;
import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.R;
import com.example.smartlibrary.activity.Book_Seat_Activity;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.util.GetTime;

/**
 * A simple {@link Fragment} subclass.
 */
public class Seat_LibraryFragment extends BaseFragment implements View.OnClickListener {

    private static Seat_LibraryFragment seat_libraryFragment = null;
    private TextView tv_today;
    private Button btn_today;
    private TextView tv_tomorrow;
    private Button btn_tomorrow;

    public static Seat_LibraryFragment getInstance() {
        if (seat_libraryFragment == null) {
            seat_libraryFragment = new Seat_LibraryFragment();
        }
        return seat_libraryFragment;
    }

    public Seat_LibraryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seat__library, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_today = (TextView) view.findViewById(R.id.tv_today);
        btn_today = (Button) view.findViewById(R.id.btn_today);
        tv_tomorrow = (TextView) view.findViewById(R.id.tv_tomorrow);
        btn_tomorrow = (Button) view.findViewById(R.id.btn_tomorrow);
        tv_today.setText(GetTime.getNowDay(0));
        tv_tomorrow.setText(GetTime.getNowDay(1));
        btn_today.setOnClickListener(this);
        btn_tomorrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(InitApp.getInstance(), Book_Seat_Activity.class);
        switch (v.getId()) {
            case R.id.btn_today:
                intent.putExtra("date","0");
                startActivity(intent);
                break;
            case R.id.btn_tomorrow:
                intent.putExtra("date","1");
                startActivity(intent);
                break;
        }
    }
}
