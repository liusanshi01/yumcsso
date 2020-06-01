package com.para4digm.yumcdpl.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static String getprnString(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return sdf.format(date);
    }
    public static void main(String[] args) {
        System.out.println(getprnString());
    }
}
