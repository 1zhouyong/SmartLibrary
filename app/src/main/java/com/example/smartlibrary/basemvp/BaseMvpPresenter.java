package com.example.smartlibrary.basemvp;

/**
 * author: ${周勇}
 * Date: 2020/4/5
 * Description:{}
 */
public abstract class BaseMvpPresenter<T> {

    public T mView;

    public void attach(T view){
        this.mView = view;
    }
    public void deattach(){
        mView = null;
    }
}
