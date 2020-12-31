package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2Activity extends Activity implements View.OnClickListener {


    //추가 변수
    final int IMAGEMAX = 9;
    MainActivity activity;
    int position;

    View t1, t2, t3, t4, t5, t6, t7, t8, t9;
    private Integer[] images = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9};
    private Integer[] thumbButton = {R.id.thumb_button_1, R.id.thumb_button_2, R.id.thumb_button_3, R.id.thumb_button_4, R.id.thumb_button_5, R.id.thumb_button_6, R.id.thumb_button_7, R.id.thumb_button_8, R.id.thumb_button_9};
    private View[] thumbView = {t1, t2, t3, t4, t5, t6, t7, t8, t9};

    public Fragment2Activity(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_2_detail);

        //fragment에서 누른 이미지 확대
        Intent intent = getIntent();
        position = intent.getExtras().getInt("id");
        zoomImageFromThumb(images[position]);

        //clickListener 설정
        for (int i = 0; i <= IMAGEMAX - 1; i++) {
            thumbView[i] = findViewById(thumbButton[i]);
            thumbView[i].setOnClickListener(this);
        }
//        View thumbView = findViewById(R.id.thumb_button_1);
//        thumbView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int index;
        for (index = 0; index < IMAGEMAX - 1; index++) {
            if (v.getId() == thumbButton[index]) { break;}
        }
        zoomImageFromThumb(images[index]);
    }

    private void zoomImageFromThumb(int imageResId) {
        final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);

        expandedImageView.setVisibility(View.VISIBLE);
    }

}
