package com.example.smartlibrary.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.example.smartlibrary.R;
import com.example.smartlibrary.base.BaseActivity;
import com.example.smartlibrary.bean.InfoBean;
import com.example.smartlibrary.bean.JsonBean;
import com.example.smartlibrary.presenter.MyInfoPresenter;
import com.example.smartlibrary.util.GetJsonDataUtil;
import com.example.smartlibrary.util.GetTime;
import com.example.smartlibrary.view.MyInfoView;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyInfoActivity extends BaseActivity<MyInfoView, MyInfoPresenter> implements MyInfoView, View.OnClickListener {


    private CircleImageView iv_head;
    private TextView tv_student;
    private TextView tv_name;
    private TextView tv_sex;
    private TextView tv_birth;
    private TextView tv_school;
    private TextView tv_college;
    private TextView tv_home;
    private Button btn_updateInfo;
    private TextView tv_tel;
    private int position;
    private ArrayList<JsonBean> options1Items = new ArrayList<>(); //省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private String[] schoolItem = {
            "南京大学","东南大学","东南大学","南京理工大学","南京工业大学","南京工业职业技术学院","江苏经贸职业技术学院","江苏联合职业技术学院等",
            "南京农业大学","南京医科大学","南京中医药大学","中国药科大学","南京财经大学","江苏警官学院","南京体育学院","南京工程学院","南京审计大学"
    };
    private String[] collageItem = {
            "国际教育学院","艺术设计学院","计算机与软件学院","电气工程学院","航空工程学院","机械工程学院","经济管理学院","交通工程学院","商务贸易学院"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        initView();
        presenter.getInfoMsg();
        initJsonData();
    }

    @Override
    public MyInfoPresenter initPresent() {
        return new MyInfoPresenter(this);
    }

    private void initView() {
        initToolbar(R.id.toolbar_activity, R.id.toolbar_text_activity, "详细资料");
        iv_head = (CircleImageView) findViewById(R.id.iv_head);
        tv_student = (TextView) findViewById(R.id.tv_student);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_birth = (TextView) findViewById(R.id.tv_birth);
        tv_school = (TextView) findViewById(R.id.tv_school);
        tv_college = (TextView) findViewById(R.id.tv_college);
        tv_home = (TextView) findViewById(R.id.tv_home);
        btn_updateInfo = (Button) findViewById(R.id.btn_updateInfo);

        btn_updateInfo.setOnClickListener(this);
        tv_tel = (TextView) findViewById(R.id.tv_tel);
        tv_tel.setOnClickListener(this);
        tv_name.setOnClickListener(this);
        tv_sex.setOnClickListener(this);
        tv_home.setOnClickListener(this);
        tv_birth.setOnClickListener(this);
        tv_school.setOnClickListener(this);
        tv_college.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_updateInfo:
                if (tv_sex.getText().toString().equals("男")){
                    presenter.updateInfo(tv_name.getText().toString(),1,
                            tv_birth.getText().toString(),tv_college.getText().toString(),
                            tv_home.getText().toString(),tv_tel.getText().toString());
                }else {
                    presenter.updateInfo(tv_name.getText().toString(),2,
                            tv_birth.getText().toString(),tv_college.getText().toString(),
                            tv_home.getText().toString(),tv_tel.getText().toString());

                }
                break;
            case R.id.tv_name:
                Intent intent = new Intent(this, UpdateInfoActivity.class);
                intent.putExtra("toolbarName","修改名字");
                intent.putExtra("type",tv_name.getText().toString());
                startActivityForResult(intent,1);
                break;
            case R.id.tv_sex:
                initDialogSex();
                break;
            case R.id.tv_home:
                initHome();
                break;
            case R.id.tv_birth:
                initBirth();
                break;
            case R.id.tv_school:
                initDialog(tv_school,schoolItem,"选择学校");
                break;
            case R.id.tv_college:
                initDialog(tv_college,collageItem,"选择学院");
                break;
            case R.id.tv_tel:
                Intent intent1 = new Intent(this, UpdateInfoActivity.class);
                intent1.putExtra("toolbarName","修改手机号码");
                intent1.putExtra("type",tv_tel.getText().toString());
                startActivityForResult(intent1,2);
                break;

        }
    }

    private void initDialog(final TextView tv, final String[] item, String title) {
        AlertDialog.Builder listDialog = new AlertDialog.Builder(this);
        listDialog.setTitle(title);
        listDialog.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv.setText(item[which]);
            }
        });
        listDialog.show();

    }

    private void initBirth() {
        TimePickerBuilder builder = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv_birth.setText(new SimpleDateFormat("yyyy年MM月dd日").format(date));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .isCenterLabel(false);
        builder.build().show();
    }

    private void initHome() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                tv_home.setText(options1Items.get(options1).getPickerViewText() + "-"
                        + options2Items.get(options1).get(options2) + "-"
                        + options3Items.get(options1).get(options2).get(options3));

            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();

    }
    private void initJsonData() {//解析数据 （省市区三级联动）
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三级）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }



    private void initDialogSex() {
        final String[] items = {"男","女","不显示"};
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("设置性别");
        dialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                position = i;
            }
        });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tv_sex.setText(items[position]);
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 1){
                tv_name.setText(data.getStringExtra("newData"));
            }else if (requestCode == 2){
                tv_tel.setText(data.getStringExtra("newData"));
            }
        }
    }

    @Override
    public void showInfo(InfoBean infoBean) {
        InfoBean.ResultBean.UserBean user = infoBean.getResult().getUser();
        tv_name.setText(user.getName());
        tv_tel.setText((String)user.getPhone());
        tv_birth.setText((String)user.getDescb());
        tv_school.setText("南京工业职业技术学院");

        if (user.getSex().toString().equals("1")){
            tv_sex.setText("男");
        }else {
            tv_sex.setText("女");
        }
        tv_college.setText((String)user.getClasses());
        tv_home.setText((String)user.getAddress());
        Glide.with(this).load(GetTime.changeImageUrl(user.getHeadPic())).into(iv_head);
    }

    @Override
    public void updateSuccess() {
        presenter.getInfoMsg();
    }

}
