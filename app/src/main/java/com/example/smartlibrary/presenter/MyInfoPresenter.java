package com.example.smartlibrary.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.smartlibrary.activity.MainActivity;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.bean.InfoBean;
import com.example.smartlibrary.bean.LoginBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.MyInfoView;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * author: ${周勇}
 * Date: 2020/4/23
 * Description:{}
 */
public class MyInfoPresenter extends BaseMvpPresenter<MyInfoView> {

    private Context context;

    public MyInfoPresenter(Context context){
        this.context = context;
    }
    public void getInfoMsg(){
        VolleyRequestUtil.RequestPostAddHeader(context, Constant.URL + "getInfo",
                "getInfo", new HashMap<String, String>(), new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        InfoBean infoBean = new Gson().fromJson(result, InfoBean.class);
                        if (infoBean.isSuccess()){
                            mView.showInfo(infoBean);
                        }
                    }

                    @Override
                    public void onMyError(VolleyError error) {

                    }
                });
    }
    public void updateInfo(String name,int sex,String birth,String college,String address, String phone){
        LoadDialog.showDialog(context);
        HashMap map = new HashMap<>();
        map.put("address",address);
        map.put("classes",college);
        map.put("descb",birth);
        map.put("id", MainActivity.userId);
        map.put("name",name);
        map.put("operator",MainActivity.studentNumber);
        map.put("phone",phone);
        map.put("sex",sex);
        VolleyRequestUtil.RequestPostAddHeader(context, Constant.URL + "user/modifyUser",
                "user/modifyUser", map, new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        LoginBean bean = new Gson().fromJson(result, LoginBean.class);
                        System.out.println(result);
                        if (bean.isSuccess()){
                            LoadDialog.showToast(context,"修改成功");
                            mView.updateSuccess();
                        }else {
                            LoadDialog.showToast(context, (String) bean.getErrorMsg());

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
