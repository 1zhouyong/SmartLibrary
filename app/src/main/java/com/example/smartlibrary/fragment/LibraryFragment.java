package com.example.smartlibrary.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.R;
import com.example.smartlibrary.activity.BookInfoActivity;
import com.example.smartlibrary.adapter.BookPageAdapter;
import com.example.smartlibrary.adapter.LibraryTypeAdapter;
import com.example.smartlibrary.base.BaseFragment;
import com.example.smartlibrary.bean.LibraryTypeBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends BaseFragment {

    private static LibraryFragment libraryFragment = null;
    private TabLayout tabLayout;


    private String[] tabTitle = {"推荐", "新书", "榜单", "连载"};
    private int[] tabPic = {R.mipmap.tuijian,
            R.mipmap.xinshu,
            R.mipmap.bangdan,
            R.mipmap.lianzai
    };
    private TextView tv_bookType;
    private GridView gridView_book;
    private LibraryTypeBean libraryTypeBean;

    public static LibraryFragment getInstance() {
        if (libraryFragment == null) {
            libraryFragment = new LibraryFragment();
        }
        return libraryFragment;
    }


    public LibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        initView(view);
        initTablayout();
        getResultBean(1);
        tv_bookType.setText("推荐");
        return view;
    }

    public void getResultBean(int flag){
        HashMap map = new HashMap<>();
        map.put("bookType", flag);
        VolleyRequestUtil.RequestPost(InitApp.getInstance(), Constant.URL + "book/select", "book/select",
                map, new VolleyListenerInterface(InitApp.getInstance(), VolleyListenerInterface.mListener, VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        libraryTypeBean = new Gson().fromJson(result, LibraryTypeBean.class);
                        LibraryTypeAdapter adapter = new LibraryTypeAdapter(InitApp.getInstance(), libraryTypeBean.getResult());
                        gridView_book.setAdapter(adapter);
                    }

                    @Override
                    public void onMyError(VolleyError error) {

                    }
                });
    }


    private void initTablayout() {
        for (int i = 0; i < 4; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }
        for (int i = 0; i < 4; i++) {
            View view = LayoutInflater.from(InitApp.getInstance()).inflate(R.layout.tab_item, null);
            ImageView pic = view.findViewById(R.id.tv_tab_pic);
            TextView text = view.findViewById(R.id.tv_tab_text);
            pic.setImageResource(tabPic[i]);
            text.setText(tabTitle[i]);
            tabLayout.getTabAt(i).setCustomView(view);
        }
    }


    private void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tv_bookType = (TextView) view.findViewById(R.id.tv_bookType);
        gridView_book = (GridView) view.findViewById(R.id.gridView_book);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getResultBean(tab.getPosition()+1);
                if (tab.getPosition()==0){
                    tv_bookType.setText("推荐");
                }else if (tab.getPosition()==1){
                    tv_bookType.setText("新书");
                }else if (tab.getPosition()==2){
                    tv_bookType.setText("榜单");
                }else if (tab.getPosition()==3){
                    tv_bookType.setText("连载");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        gridView_book.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("book",libraryTypeBean.getResult().get(i));
                Intent intent = new Intent(InitApp.getInstance(), BookInfoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
