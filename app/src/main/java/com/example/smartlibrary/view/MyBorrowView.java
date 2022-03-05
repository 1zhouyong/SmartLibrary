package com.example.smartlibrary.view;

import com.example.smartlibrary.bean.MyBorrowBean;

import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/5/2
 * Description:{}
 */
public interface MyBorrowView {

    void showMyBorrow(List<MyBorrowBean.ResultBean> resultBeans);

    void showReturnmsg(String msg);
}
