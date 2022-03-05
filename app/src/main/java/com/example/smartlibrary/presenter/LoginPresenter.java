package com.example.smartlibrary.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.VolleyError;
import com.example.smartlibrary.util.Constant;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.bean.LoginBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.view.LoginView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * author: ${周勇}
 * Date: 2020/4/5
 * Description:{}
 */
public class LoginPresenter extends BaseMvpPresenter<LoginView> {

    private Context mContext;
    private LoginView loginView;
    private SharedPreferences.Editor sp;

    public LoginPresenter(Context context) {
        this.mContext = context;
    }

    public void getData(boolean checked, String account, String password) {
        login(mContext, checked, account, password);
    }

    private void login(final Context context, final boolean checked, final String account, final String password) {
        mView.showLoad();
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("studyId", account);
        loginMap.put("password", password);

        VolleyRequestUtil.RequestPost(context, Constant.URL + "login", "login", loginMap,
                new VolleyListenerInterface(context, VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);
                        if (loginBean.isSuccess()) {
                            mView.showToken(loginBean.getResult());
                            sp = context.getSharedPreferences("login", Context.MODE_PRIVATE).edit();
                            if (checked){
                                sp.putString("username", account);
                                sp.putString("password", password);
                                sp.apply();
                            }else {
                                sp.putString("password", "");
                                sp.apply();
                            }

                        }
                        mView.showMessage(loginBean.isSuccess());
                        mView.hideLoad();
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        System.out.println("error:"+error);
                        mView.hideLoad();
                    }
                });
    }


}
