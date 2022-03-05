package com.example.smartlibrary.presenter;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.VolleyError;
import com.example.smartlibrary.activity.MainActivity;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.bean.BookComment;
import com.example.smartlibrary.bean.LibraryTypeBean;
import com.example.smartlibrary.bean.LoginBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.BookInfoView;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * author: ${周勇}
 * Date: 2020/4/26
 * Description:{}
 */
public class BookInfoPresenter extends BaseMvpPresenter<BookInfoView> {

    private Context context;
    private LibraryTypeBean.ResultBean resultBean;

    public BookInfoPresenter(Context context){
        this.context = context;
    }

    public void getBookInfo(Bundle bundle){
        resultBean = (LibraryTypeBean.ResultBean) bundle.getSerializable("book");
      mView.showBookInfo(resultBean);
    }
    public void addBook(int bookId){
        LoadDialog.showDialog(context);
        HashMap map = new HashMap<>();
        map.put("bookId",bookId);
        map.put("userId", MainActivity.userId);
        VolleyRequestUtil.RequestPost(context, Constant.URL+"book/addBookToMy", "book/addBookToMy", map,
                new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);
                        if (loginBean.isSuccess()){
                            mView.addBookMsg("已添加至我的书架");
                        }else {
                            mView.addBookMsg((String) loginBean.getErrorMsg());
                        }
                        LoadDialog.disDialog();
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        LoadDialog.disDialog();
                    }
                });
    }
    public void getBookComment(int bookId){
        HashMap map = new HashMap<>();
        map.put("bookId",bookId);
        map.put("userId",MainActivity.userId);
        VolleyRequestUtil.RequestPost(context, Constant.URL + "book/queryBookCommentByBookId", "book/queryBookCommentByBookId",
                map, new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        BookComment bookComment = new Gson().fromJson(result, BookComment.class);
                        if (bookComment.isSuccess()){
                            if (bookComment.getResult().size()==0){
                                mView.noData();
                            }else {
                                mView.showBookComment(bookComment.getResult());
                            }
                        }
                    }

                    @Override
                    public void onMyError(VolleyError error) {

                    }
                });
    }
    public void DianZhan(int commentId){
        HashMap map = new HashMap<>();
        map.put("commentId",commentId);
        map.put("userId",MainActivity.userId);
        VolleyRequestUtil.RequestPost(context, Constant.URL + "book/bookCommentPraise",
                "book/bookCommentPraise", map, new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {

                    }

                    @Override
                    public void onMyError(VolleyError error) {

                    }
                });
    }
    public void addComment(String comment){
        LoadDialog.showDialog(context);
        HashMap map = new HashMap<>();
        map.put("bookId",resultBean.getId());
        map.put("comment",comment);
        map.put("userName",MainActivity.studentNumber);
        VolleyRequestUtil.RequestPost(context, Constant.URL + "book/addBookComment",
                "book/addBookComment", map, new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        LoginBean bean = new Gson().fromJson(result, LoginBean.class);
                        if (bean.isSuccess()){
                            mView.dianzhanSucceed();
                        }
                        LoadDialog.disDialog();
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        LoadDialog.disDialog();

                    }
                });
    }
    public void ReplyComment(String comment,int parentId){
        HashMap map = new HashMap<>();
        map.put("bookId",resultBean.getId());
        map.put("comment",comment);
        map.put("parentId",parentId);
        map.put("userName",MainActivity.studentNumber);
        VolleyRequestUtil.RequestPost(context, Constant.URL + "book/addBookCommentRecive",
                "book/addBookCommentRecive", map, new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        LoginBean bean = new Gson().fromJson(result, LoginBean.class);
                        if (bean.isSuccess()){
                            mView.applyComment();
                        }
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                    }
                });
    }
}
