package com.example.smartlibrary.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.R;
import com.example.smartlibrary.adapter.LibraryTypeAdapter;
import com.example.smartlibrary.bean.LibraryTypeBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class BookTypeFragment extends Fragment {

    private TextView tv_bookType;
    private GridView gridView_book;
    private int flag;

    public BookTypeFragment(int flag) {
        // Required empty public constructor
        this.flag = flag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_type, container, false);
        initView(view);
        initData();
        return view;
    }


    private void initData() {
            tv_bookType.setText("推荐");

        }


    private void initView(View view) {
        tv_bookType = (TextView) view.findViewById(R.id.tv_bookType);
        gridView_book = (GridView) view.findViewById(R.id.gridView_book);
    }


}
