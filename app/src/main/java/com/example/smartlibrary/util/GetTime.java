package com.example.smartlibrary.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * author: ${周勇}
 * Date: 2020/4/7
 * Description:{}
 */
public class GetTime {
    public static String getNowDay(int i){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, i);//-1.昨天时间 0.当前时间 1.明天时间 *以此类推
        String time = sdf.format(c.getTime());
        return time;
    }
    public static String getNowDay1(int i){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, i);//-1.昨天时间 0.当前时间 1.明天时间 *以此类推
        String time = sdf.format(c.getTime());
        return time;
    }

    public static String changeImageUrl(String uri){
        String replace = uri.replaceAll("E:\\\\tmp\\\\libary\\\\web\\\\src\\\\main\\\\resources\\\\static\\\\",
                Constant.URL);
        String replace1 = replace.replaceAll("\\\\", "/");
        return replace1;

    }
}
