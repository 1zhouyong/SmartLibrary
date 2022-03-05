package com.example.smartlibrary.view;

import com.example.smartlibrary.bean.LectureListBean;
import com.example.smartlibrary.bean.MyLectureBean;

import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/4/20
 * Description:{}
 */
public interface MyLectureView {

    void showMyLecture(List<LectureListBean.ResultBean> list);

    void showMsg(String text);
}
