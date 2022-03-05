package com.example.smartlibrary;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.HashSet;
import java.util.Set;

/**
 * author: ${周勇}
 * Date: 2020/4/4
 * Description:{}
 */
public class InitApp extends Application {

    private static InitApp instance;
    private static Handler mainHandler;
    private Set<Activity> allActivities;
    public static RequestQueue queue;


    public static synchronized InitApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mainHandler = new Handler();
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getQueue(){
        return queue;
    }

    public static Handler getMainHandler() {
        return mainHandler;
    }

    public void addActitivity(Activity activity) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (allActivities != null) {
            allActivities.remove(activity);
        }
    }
    public void removeAllActivity(){
        allActivities.clear();
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity activity : allActivities) {
                    activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
