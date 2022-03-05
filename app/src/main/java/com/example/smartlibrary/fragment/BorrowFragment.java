package com.example.smartlibrary.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.R;
import com.example.smartlibrary.activity.MainActivity;
import com.example.smartlibrary.activity.ReadBookActivity;
import com.example.smartlibrary.adapter.MyBookAdapter;
import com.example.smartlibrary.base.BaseFragment;
import com.example.smartlibrary.bean.MyBookBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class BorrowFragment extends BaseFragment {

    private static BorrowFragment borrowFragment = null;
    private GridView gridView_mybook;
    private MyBookBean myBookBean;

    public static BorrowFragment getInstance() {
        if (borrowFragment == null) {
            borrowFragment = new BorrowFragment();
        }
        return borrowFragment;
    }


    public BorrowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_borrow, container, false);
        initView(view);
        getMyBookNews();
        return view;
    }
    public void getMyBookNews(){
        HashMap map = new HashMap<>();
        map.put("userId", MainActivity.userId);
        VolleyRequestUtil.RequestPost(InitApp.getInstance(), Constant.URL + "book/queryBookToMy",
                "book/queryBookToMy", map, new VolleyListenerInterface(InitApp.getInstance(),VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        myBookBean = new Gson().fromJson(result, MyBookBean.class);
                        if (myBookBean.isSuccess()){
                            MyBookAdapter adapter = new MyBookAdapter(InitApp.getInstance(), myBookBean.getResult());
                            gridView_mybook.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onMyError(VolleyError error) {

                    }
                });
    }

    private void initView(View view) {
        gridView_mybook = (GridView) view.findViewById(R.id.gridView_mybook);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            getMyBookNews();
        }
    }
}
