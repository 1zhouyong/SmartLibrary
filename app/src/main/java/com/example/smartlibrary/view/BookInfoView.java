package com.example.smartlibrary.view;

import com.example.smartlibrary.bean.BookComment;
import com.example.smartlibrary.bean.LibraryTypeBean;

import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/4/26
 * Description:{}
 */
public interface BookInfoView {

    void showBookInfo(LibraryTypeBean.ResultBean resultBean);

    void addBookMsg(String msg);

    void showBookComment(List<BookComment.ResultBean> resultBeans);

    void noData();

    void dianzhanSucceed();

    void applyComment();
}
