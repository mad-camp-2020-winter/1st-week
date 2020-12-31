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
    final int IMAGEMAX = 21;
    MainActivity activity;
    int position;

    View t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21;
    private Integer[] images = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16, R.drawable.a17, R.drawable.a18, R.drawable.a19, R.drawable.a20, R.drawable.a21};
    private Integer[] thumbButton = {R.id.thumb_button_1, R.id.thumb_button_2, R.id.thumb_button_3, R.id.thumb_button_4, R.id.thumb_button_5, R.id.thumb_button_6, R.id.thumb_button_7, R.id.thumb_button_8, R.id.thumb_button_9, R.id.thumb_button_10, R.id.thumb_button_11, R.id.thumb_button_12, R.id.thumb_button_13, R.id.thumb_button_14, R.id.thumb_button_15, R.id.thumb_button_16, R.id.thumb_button_17, R.id.thumb_button_18, R.id.thumb_button_19, R.id.thumb_button_20, R.id.thumb_button_21};
    private View[] thumbView = {t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21};

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
            thumbView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index;
                    for (index = 0; index < IMAGEMAX - 1; index++) {
                        if (v.getId() == thumbButton[index]) { break;}
                    }
                    zoomImageFromThumb(images[index]);
                }
            });
        }

        switchHeartListener();
    }

    public void switchHeartListener() {
        View heartView = findViewById(R.id.heart);
        View heartBlankView = findViewById(R.id.heart_blank);
        heartView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switchHeart();
            }
        });
        heartBlankView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switchHeart();
            }
        });
    }

    public void switchHeart(){
        final ImageView heartView = (ImageView) findViewById(R.id.heart);
        final ImageView heartBlankView = (ImageView) findViewById(R.id.heart_blank);
        if (heartView.getVisibility() == View.VISIBLE) {
            heartView.setVisibility(View.INVISIBLE);
            heartBlankView.setVisibility(View.VISIBLE);
        }
        else if (heartView.getVisibility() == View.INVISIBLE) {
            heartView.setVisibility(View.VISIBLE);
            heartBlankView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

    }

    private void zoomImageFromThumb(int imageResId) {
        final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);

        expandedImageView.setVisibility(View.VISIBLE);
    }

}
