package com.example.smartlibrary.view;

import com.example.smartlibrary.bean.SeatInfomationBean;

import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/4/14
 * Description:{}
 */
public interface MySeatView {

    void showSeatInfo(List<SeatInfomationBean> seatInfomationBeanList);

    void cancelSuccess();
}
