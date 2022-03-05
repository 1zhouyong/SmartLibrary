package com.example.smartlibrary.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.R;
import com.example.smartlibrary.bean.InfoBean;
import com.example.smartlibrary.fragment.BorrowFragment;
import com.example.smartlibrary.fragment.LibraryFragment;
import com.example.smartlibrary.fragment.SeatFragment;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyMultipartRequest;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.example.smartlibrary.util.GetTime;
import com.example.smartlibrary.util.ImageUtils3;
import com.example.smartlibrary.util.LoadDialog;
import com.google.gson.Gson;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileWithBitmapCallback;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final int FRAGMENT_LIBRARY = 0;
    private static final int FRAGMENT_BORROW = 1;
    private static final int FRAGMENT_SEAT = 2;
    private static final String POSITION = "position";
    private static final String SELECT_ITEM = "bottomItem";
    private Toolbar toolbar;
    private AppBarLayout AppBarLayout01;
    private FrameLayout frame_main;
    private BottomNavigationView bottom_nav;
    private NavigationView nav_view;
    private DrawerLayout drawer_layout;
    private LibraryFragment libraryFragment;
    private BorrowFragment borrowFragment;
    private SeatFragment seatFragment;
    private CircleImageView nav_head_pic;
    private TextView nav_head_name;
    public static int userId;
    public static String userName;
    public static String studentNumber;
    public static String headUrl;

    //调取系统摄像头的请求码
    private static final int MY_ADD_CASE_CALL_PHONE = 6;
    //打开相册的请求码
    private static final int MY_ADD_CASE_CALL_PHONE2 = 7;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private LayoutInflater inflater;
    private View layout;
    private TextView takePhotoTV;
    private TextView choosePhotoTV;
    private TextView cancelTV;
    private Thread thread;
    private static WeakReference<MainActivity> sActivityRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sActivityRef = new WeakReference<>(this);
        initView();
        initData();
        if (savedInstanceState != null) {
            loadMultipleRootFragment(R.id.container, 0, libraryFragment, borrowFragment, seatFragment);   //使用fragmentation加载根组件
            // 恢复 recreate 前的位置
            showFragment(savedInstanceState.getInt(POSITION));
            bottom_nav.setSelectedItemId(savedInstanceState.getInt(SELECT_ITEM));
        } else {
            showFragment(FRAGMENT_LIBRARY);
        }
        getInformation();

    }
    public static void finishActivity() {
        if (sActivityRef != null && sActivityRef.get() != null) {
            sActivityRef.get().finish();
        }
    }
    private void getInformation() {

        VolleyRequestUtil.RequestPostAddHeader(this, Constant.URL+"getInfo",
                "getInfo", new HashMap<String, String>(), new VolleyListenerInterface(this,
                        VolleyListenerInterface.mListener, VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        InfoBean infoBean = new Gson().fromJson(result, InfoBean.class);
                        if (infoBean.isSuccess()) {
                            nav_head_name.setText(infoBean.getResult().getUser().getName());
                            System.out.println(GetTime.changeImageUrl(infoBean.getResult().getUser().getHeadPic()));
                            thread = new Thread(){
                                 @Override
                                 public void run() {
                                     Glide.get(MainActivity.this).clearDiskCache();
                                     super.run();
                                 }
                             };
                           thread.start();
                           headUrl = (String) infoBean.getResult().getUser().getHeadPic();
                            Glide.with(MainActivity.this).
                                    load(GetTime.changeImageUrl((String) infoBean.getResult().getUser()
                                            .getHeadPic())).into(nav_head_pic);
                            userId = infoBean.getResult().getUser().getId();
                            userName = infoBean.getResult().getUser().getName();
                            studentNumber = infoBean.getResult().getUser().getStudyId();
                        }
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                    }
                });
    }

    private void initData() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        drawer_layout.addDrawerListener(toggle);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_library:
                        showFragment(FRAGMENT_LIBRARY);
                        break;
                    case R.id.action_borrow:
                        showFragment(FRAGMENT_BORROW);
                        break;
                    case R.id.action_seat:
                        showFragment(FRAGMENT_SEAT);
                        break;
                }
                return true;
            }
        });
    }

    private void showFragment(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (position) {
            case FRAGMENT_LIBRARY:
                toolbar.setTitle("图书馆");

                if (libraryFragment == null) {
                    libraryFragment = LibraryFragment.getInstance();
                    ft.add(R.id.frame_main, libraryFragment, LibraryFragment.class.getName());
                } else {
                    ft.show(libraryFragment);
                }
                break;
            case FRAGMENT_BORROW:
                toolbar.setTitle("我的书架");
                if (borrowFragment == null) {
                    borrowFragment = BorrowFragment.getInstance();
                    ft.add(R.id.frame_main, borrowFragment, BorrowFragment.class.getName());
                } else {
                    ft.show(borrowFragment);
                }
                break;
            case FRAGMENT_SEAT:
                toolbar.setTitle("预约座位");
                if (seatFragment == null) {
                    seatFragment = SeatFragment.getInstance();
                    ft.add(R.id.frame_main, seatFragment, SeatFragment.class.getName());
                } else {
                    ft.show(seatFragment);
                }
                break;

        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (libraryFragment != null) {
            ft.hide(libraryFragment);
        }
        if (borrowFragment != null) {
            ft.hide(borrowFragment);
        }
        if (seatFragment != null) {
            ft.hide(seatFragment);
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setBackgroundColor(Color.parseColor("#008B8B"));
        AppBarLayout01 = (AppBarLayout) findViewById(R.id.AppBarLayout01);
        frame_main = (FrameLayout) findViewById(R.id.frame_main);
        bottom_nav = (BottomNavigationView) findViewById(R.id.bottom_nav);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_head_pic = nav_view.getHeaderView(0).findViewById(R.id.nav_head_pic);

        nav_head_name = nav_view.getHeaderView(0).findViewById(R.id.nav_head_name);
        nav_view.setNavigationItemSelectedListener(this);
        nav_head_pic.setOnClickListener(this);

    }

    @Override
    public void onBackPressedSupport() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {    /*打开或关闭左边的菜单*/
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressedSupport();
            showExitDialog();
        }
    }

    /*是否退出项目*/
    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                InitApp.getInstance().exitApp();
            }
        });
        builder.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_account:
                startActivity(new Intent(MainActivity.this,MyInfoActivity.class));
                break;
            case R.id.item_2:
                startActivity(new Intent(MainActivity.this,MyBorrowActivity.class));
                break;
            case R.id.item_3:
                startActivity(new Intent(MainActivity.this, MySeatActivity.class));
                break;
            case R.id.item_4:
                startActivity(new Intent(MainActivity.this, MyLectureActivity.class));
                break;
            case R.id.changePassword:
                startActivity(new Intent(MainActivity.this, ChangePasswordActivity.class));
                break;
            case R.id.nav_setting:
                showExitDialog();
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_head_pic:
                viewInit();
                break;
            case R.id.photograph:
                //"点击了照相";
                //  6.0之后动态申请权限 摄像头调取权限,SD卡写入权限
                //判断是否拥有权限，true则动态申请
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_ADD_CASE_CALL_PHONE);
                } else {
                    try {
                        //有权限,去打开摄像头
                        takePhoto();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
                break;
            case R.id.photo:
                //"点击了相册";
                //  6.0之后动态申请权限 SD卡写入权限
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_ADD_CASE_CALL_PHONE2);

                } else {
                    //打开相册
                    choosePhoto();
                }
                dialog.dismiss();
                break;
            case R.id.cancel:
                dialog.cancel();
                break;

        }

    }

    /**
     * 打开相册
     */
    private void choosePhoto() {
        //这是打开系统默认的相册(就是你系统怎么分类,就怎么显示,首先展示分类列表)
        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picture, 2);
    }

    /*
初始化控件方法
 */
    public void viewInit() {

        builder = new AlertDialog.Builder(this);//创建对话框
        inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.dialog_select_photo, null);//获取自定义布局
        builder.setView(layout);//设置对话框的布局
        dialog = builder.create();//生成最终的对话框
        dialog.show();//显示对话框

        takePhotoTV = layout.findViewById(R.id.photograph);
        choosePhotoTV = layout.findViewById(R.id.photo);
        cancelTV = layout.findViewById(R.id.cancel);
        //设置监听
        takePhotoTV.setOnClickListener(this);
        choosePhotoTV.setOnClickListener(this);
        cancelTV.setOnClickListener(this);
    }

    private void takePhoto() throws IOException {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        // 获取文件
        File file = createFileIfNeed("UserIcon.png");
        //拍照后原图回存入此路径下
        Uri uri;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            uri = Uri.fromFile(file);
        } else {
            /**
             * 7.0 调用系统相机拍照不再允许使用Uri方式，应该替换为FileProvider
             * 并且这样可以解决MIUI系统上拍照返回size为0的情况
             */
            uri = FileProvider.getUriForFile(this, "com.example.bobo.getphotodemo.fileprovider1", file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, 1);
    }

    // 在sd卡中创建一保存图片（原图和缩略图共用的）文件夹
    private File createFileIfNeed(String fileName) throws IOException {
        String fileA = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/nbinpic";
        File fileJA = new File(fileA);
        if (!fileJA.exists()) {
            fileJA.mkdirs();
        }
        File file = new File(fileA, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode != Activity.RESULT_CANCELED) {

            String state = Environment.getExternalStorageState();
            if (!state.equals(Environment.MEDIA_MOUNTED)) return;
            // 把原图显示到界面上
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            Tiny.getInstance().source(readpic()).asFile().withOptions(options).compress(new FileWithBitmapCallback() {
                @Override
                public void callback(boolean isSuccess, Bitmap bitmap, String outfile, Throwable t) {
                    saveImageToServer(bitmap, outfile);//显示图片到imgView上
                }
            });
        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK
                && null != data) {
            try {
                final Uri selectedImage = data.getData();//获取路径
                Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                Tiny.getInstance().source(selectedImage).asFile().withOptions(options).compress(new FileWithBitmapCallback() {
                    @Override
                    public void callback(boolean isSuccess, Bitmap bitmap, String outfile, Throwable t) {
                        saveImageToServer(bitmap, outfile);

                    }
                });
            } catch (Exception e) {
                //"上传失败");
            }
        }
    }

    /**
     * 从保存原图的地址读取图片
     */
    private String readpic() {
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/nbinpic/" + "UserIcon.png";
        return filePath;
    }

    private void saveImageToServer(final Bitmap bitmap, String outfile) {
        File file = new File(outfile);
        // TODO: 2018/12/4  这里就可以将图片文件 file 上传到服务器,上传成功后可以将bitmap设置给你对应的图片展示
        uploadBitmap(bitmap);
        nav_head_pic.setImageBitmap(bitmap);
    }

    private void uploadBitmap(final Bitmap bitmap) {
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST,
                Constant.URL+"user/modifyHeadPic",
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        LoadDialog.showToast(MainActivity.this,"上传成功");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
                RetryPolicy retryPolicy1 = new RetryPolicy() {
                    @Override
                    public int getCurrentTimeout() {
                        return 15000;
                    }

                    @Override
                    public int getCurrentRetryCount() {
                        return 5;
                    }

                    @Override
                    public void retry(VolleyError error) throws VolleyError {

                    }
                };
                return super.setRetryPolicy(retryPolicy1);
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap params = new HashMap<>();
                params.put("id", String.valueOf(userId));
                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap<>();
                header.put("Login-Pass", LoginActivity.loginToken);
                return header;
            }

            @Override
            protected Map<String, VolleyMultipartRequest.DataPart> getByteData() throws AuthFailureError {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("headPic", new DataPart(imagename + ".jpeg", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };
        InitApp.queue.add(volleyMultipartRequest);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }

}
