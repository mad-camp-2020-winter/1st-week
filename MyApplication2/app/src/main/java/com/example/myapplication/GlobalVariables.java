package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class GlobalVariables {
    final static int IMAGEMAX = 21;

    public static Integer[] images = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16, R.drawable.a17, R.drawable.a18, R.drawable.a19, R.drawable.a20, R.drawable.a21};
    public static Integer[] thumbButton = {R.id.thumb_button_1, R.id.thumb_button_2, R.id.thumb_button_3, R.id.thumb_button_4, R.id.thumb_button_5, R.id.thumb_button_6, R.id.thumb_button_7, R.id.thumb_button_8, R.id.thumb_button_9, R.id.thumb_button_10, R.id.thumb_button_11, R.id.thumb_button_12, R.id.thumb_button_13, R.id.thumb_button_14, R.id.thumb_button_15, R.id.thumb_button_16, R.id.thumb_button_17, R.id.thumb_button_18, R.id.thumb_button_19, R.id.thumb_button_20, R.id.thumb_button_21};
    public static ArrayList<Integer> bookMarks = new ArrayList<Integer>();

    private static GlobalVariables instance = null;
    public static synchronized GlobalVariables getInstance() {
        if(null == instance) {
            instance = new GlobalVariables();
        }
        return instance;
    }

    public static void addBookMarks(int ImageResId) {
        if(!bookMarks.contains(ImageResId)) { bookMarks.add(ImageResId); }
        System.out.println(bookMarks.toString());
    }

    public static void removeBookMarks(int ImageResId) {
        if(bookMarks.contains(ImageResId)) { bookMarks.remove( (Integer) ImageResId);}
        System.out.println(bookMarks.toString());
    }

    public static boolean inBookMarks(int ImageResId) {
        if(bookMarks.contains(ImageResId)) { return true;}
        return false;
    }



}
