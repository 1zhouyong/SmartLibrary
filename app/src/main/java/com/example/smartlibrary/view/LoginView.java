package com.example.smartlibrary.view;

import com.example.smartlibrary.basemvp.BaseView;

/**
 * author: ${周勇}
 * Date: 2020/4/5
 * Description:{}
 */
public interface LoginView extends BaseView {

    void showMessage(boolean msg);

    void showToken(String token);

}
