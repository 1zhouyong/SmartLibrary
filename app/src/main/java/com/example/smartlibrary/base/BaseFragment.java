package com.example.smartlibrary.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * author: ${周勇}
 * Date: 2020/4/4
 * Description:{}
 */
public abstract class BaseFragment extends SupportFragment {

    protected Activity activity;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        activity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
