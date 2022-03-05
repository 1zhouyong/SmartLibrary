package com.example.smartlibrary.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.smartlibrary.activity.MainActivity;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.bean.LoginBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.ChangePasswordView;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * author: ${周勇}
 * Date: 2020/4/23
 * Description:{}
 */
public class ChangePasswordPresenter extends BaseMvpPresenter<ChangePasswordView> {

    private Context context;

    public ChangePasswordPresenter(Context context) {
        this.context = context;
    }

    public void changePassword(String newPassword){
        LoadDialog.showDialog(context);
        HashMap map = new HashMap<>();
        map.put("newPwd",newPassword);
        map.put("studyId", MainActivity.studentNumber);
        VolleyRequestUtil.RequestPostAddHeader(context, Constant.URL + "user/modifyPwd",
                "user/modifyPwd", map, new VolleyListenerInterface(context,
                        VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);
                        if (loginBean.isSuccess()){
                            mView.showMsg("修改成功");
                        }else {
                            mView.showMsg((String) loginBean.getErrorMsg());
                        }
                        LoadDialog.disDialog();
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        LoadDialog.disDialog();
                    }
                });
    }
}
