package com.example.smartlibrary.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.R;
import com.example.smartlibrary.activity.BookLectureActivity;
import com.example.smartlibrary.adapter.LectureListAdapter;
import com.example.smartlibrary.base.BaseFragment;
import com.example.smartlibrary.bean.LectureListBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Seat_Lecture_Fragment extends BaseFragment{

    private static Seat_Lecture_Fragment seat_lecture_fragment = null;
    private ListView list_lecture;
    private LectureListBean lectureListBean;

    public static Seat_Lecture_Fragment getInstance() {
        if (seat_lecture_fragment == null) {
            seat_lecture_fragment = new Seat_Lecture_Fragment();
        }
        return seat_lecture_fragment;
    }

    public Seat_Lecture_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seat__lecture_, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        VolleyRequestUtil.RequestPostAddHeader(InitApp.getInstance(), Constant.URL + "lecture/select", "lecture/select",
                new HashMap<String, String>(), new VolleyListenerInterface(InitApp.getInstance(),VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        lectureListBean = new Gson().fromJson(result, LectureListBean.class);
                        LectureListAdapter adapter = new LectureListAdapter(InitApp.getInstance(), lectureListBean);
                        list_lecture.setAdapter(adapter);
                        list_lecture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("lectureList",lectureListBean.getResult().get(i));
                                Intent intent = new Intent(InitApp.getInstance(), BookLectureActivity.class);
                                intent.putExtras(bundle);
                                intent.putExtra("flag","no");
                                startActivity(intent);
                            }
                        });

                    }

                    @Override
                    public void onMyError(VolleyError error) {

                    }
                });
    }

    private void initView(View view) {
        list_lecture = (ListView) view.findViewById(R.id.list_lecture);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
