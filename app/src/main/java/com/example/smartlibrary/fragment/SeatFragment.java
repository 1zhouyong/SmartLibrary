package com.example.smartlibrary.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartlibrary.base.BaseFragment;
import com.example.smartlibrary.R;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeatFragment extends BaseFragment implements View.OnClickListener {

    private static final int FRAGMENT_SEAT = 0;
    private static final int FRAGMENT_LECTURE = 1;
    private static SeatFragment seatFragment = null;
    private TextView tv_seat;
    private View tv_line_1;
    private TextView tv_lecture;
    private View tv_line_2;
    private Seat_LibraryFragment seat_libraryFragment;
    private Seat_Lecture_Fragment seat_lecture_fragment;
    private static final String POSITION = "position";

    public static SeatFragment getInstance() {
        if (seatFragment == null) {
            seatFragment = new SeatFragment();
        }
        return seatFragment;
    }

    public SeatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seat, container, false);
        initView(view);
        if (savedInstanceState != null) {
            loadMultipleRootFragment(R.id.frame_seat,0,seat_libraryFragment, seat_lecture_fragment,seatFragment);   //使用fragmentation加载根组件
            // 恢复 recreate 前的位置
            showFragment(savedInstanceState.getInt(POSITION));
        } else {
            showFragment(FRAGMENT_SEAT);
        }

        return view;
    }

    private void showFragment(int i) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        if ( i == 0){
            if (seat_libraryFragment == null){
                seat_libraryFragment = Seat_LibraryFragment.getInstance();
                ft.add(R.id.frame_seat,seat_libraryFragment,Seat_LibraryFragment.class.getName());
            }else {
                ft.show(seat_libraryFragment);
            }
        }else {
            if (seat_lecture_fragment == null){
                seat_lecture_fragment = Seat_Lecture_Fragment.getInstance();
                ft.add(R.id.frame_seat,seat_lecture_fragment,Seat_LibraryFragment.class.getName());
            }else {
                ft.show(seat_lecture_fragment);
            }
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (seat_lecture_fragment != null){
            ft.hide(seat_lecture_fragment);
        }
        if (seat_libraryFragment != null){
            ft.hide(seat_libraryFragment);
        }
    }

    private void initView(View view) {
        tv_seat = (TextView) view.findViewById(R.id.tv_seat);
        tv_seat.setOnClickListener(this);
        tv_line_1 = (View) view.findViewById(R.id.tv_line_1);
        tv_line_1.setOnClickListener(this);
        tv_lecture = (TextView) view.findViewById(R.id.tv_lecture);
        tv_lecture.setOnClickListener(this);
        tv_line_2 = (View) view.findViewById(R.id.tv_line_2);
        tv_line_2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_seat:
                showFragment(FRAGMENT_SEAT);
                tv_seat.setTextSize(25);
                tv_line_1.setBackgroundColor(Color.parseColor("#FFD700"));
                tv_lecture.setTextSize(20);
                tv_line_2.setBackgroundColor(Color.TRANSPARENT);
                break;
            case R.id.tv_lecture:
                showFragment(FRAGMENT_LECTURE);
                tv_lecture.setTextSize(25);
                tv_line_2.setBackgroundColor(Color.parseColor("#FFD700"));
                tv_seat.setTextSize(20);
                tv_line_1.setBackgroundColor(Color.TRANSPARENT);
                break;
        }
    }
}
