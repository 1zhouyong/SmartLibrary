package com.example.smartlibrary.view;

import com.example.smartlibrary.bean.LectureListBean;

import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/4/19
 * Description:{}
 */
public interface BookLectureView {

    void showResultBean(LectureListBean.ResultBean list);

    void showMessage(String msg);
}
