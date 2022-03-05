package com.example.smartlibrary.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.smartlibrary.activity.MainActivity;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.bean.MyBookBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.example.smartlibrary.view.MyBookView;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * author: ${周勇}
 * Date: 2020/5/1
 * Description:{}
 */
public class MyBookPresenter extends BaseMvpPresenter<MyBookView> {

    private Context context;

    public MyBookPresenter(Context context){
        this.context = context;
    }

    public void getMyBookNews(){
        HashMap map = new HashMap<>();
        map.put("userId", MainActivity.userId);
        VolleyRequestUtil.RequestPost(context, Constant.URL + "book/queryBookToMy",
                "book/queryBookToMy", map, new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        MyBookBean myBookBean = new Gson().fromJson(result, MyBookBean.class);
                        if (myBookBean.isSuccess()){
                            mView.showMyBook(myBookBean.getResult());
                        }
                    }

                    @Override
                    public void onMyError(VolleyError error) {

                    }
                });
    }


}
