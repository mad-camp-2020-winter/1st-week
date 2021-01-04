package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Tab3Male extends Activity {


    private Integer[] male = GlobalVariables.male;
    private Integer[] arr_round16 = GlobalVariables.shuffle(male);
    private Integer[] arr_round8 = new Integer[8];
    private Integer[] arr_round4 = new Integer[4];
    private Integer[] arr_round2 = new Integer[2];

    int countRound16 = 0;
    int countRound8 = 0;
    int countRound4 = 0;
    int countRound2 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3_male);

        final TextView male_round16 = findViewById(R.id.male_round16);
        final TextView male_round8 = findViewById(R.id.male_round8);
        final TextView male_round4 = findViewById(R.id.male_round4);
        final TextView male_round2 = findViewById(R.id.male_round2);

        male_round8.setVisibility(View.INVISIBLE);
        male_round4.setVisibility(View.INVISIBLE);
        male_round2.setVisibility(View.INVISIBLE);
        male_round16.bringToFront();
        male_round8.bringToFront();
        male_round4.bringToFront();
        male_round2.bringToFront();



        ImageButton button1 = findViewById(R.id.male_first_button);
        ImageButton button2 = findViewById(R.id.male_second_button);

        button1.setImageResource(arr_round16[0]);
        button2.setImageResource(arr_round16[1]);

        countRound16 = countRound16 + 1;

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //button2.setImageResource(arr_round16[0]);

                /*
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                 */

                //일단 기존의 데이터를 10ms간 유지

                if (countRound16<=8){
                    //선택된 이미지를 다음 항목 리스트에 삽입하기
                    arr_round8[countRound16-1] = arr_round16[(countRound16-1)*2];
                    System.out.println(countRound16);
                    if (countRound16 ==8){
                        male_round8.setVisibility(View.VISIBLE);
                        male_round16.setVisibility(View.INVISIBLE);

                        System.out.println(arr_round8.length);
                        arr_round8 = GlobalVariables.shuffle(arr_round8);
                        button1.setImageResource(arr_round8[0]);
                        button2.setImageResource(arr_round8[1]);
                        countRound8 = countRound8 + 1;
                    }
                    else {
                        //다음에 나올 이미지 띄우기
                        button1.setImageResource(arr_round16[countRound16 * 2]);
                        button2.setImageResource(arr_round16[countRound16 * 2 + 1]);
                    }

                    countRound16 = countRound16 + 1;
                }

                else if(countRound8<=4){

                    arr_round4[countRound8-1] = arr_round8[(countRound8-1)*2];

                    if (countRound8 ==4){
                        male_round4.setVisibility(View.VISIBLE);
                        male_round8.setVisibility(View.INVISIBLE);
                        arr_round4 = GlobalVariables.shuffle(arr_round4);
                        button1.setImageResource(arr_round4[0]);
                        button2.setImageResource(arr_round4[1]);
                        countRound4 = countRound4 + 1;
                    }
                    else {
                        //다음에 나올 이미지 띄우기
                        button1.setImageResource(arr_round8[countRound8 * 2]);
                        button2.setImageResource(arr_round8[countRound8 * 2 + 1]);
                    }

                    countRound8 = countRound8 + 1;
                }

                else if(countRound4<=2){

                    arr_round2[countRound4-1] = arr_round4[(countRound4-1)*2];

                    if (countRound4 ==2){
                        male_round2.setVisibility(View.VISIBLE);
                        male_round4.setVisibility(View.INVISIBLE);
                        arr_round2 = GlobalVariables.shuffle(arr_round2);
                        button1.setImageResource(arr_round2[0]);
                        button2.setImageResource(arr_round2[1]);
                        countRound2 = countRound2 + 1;
                    }
                    else {
                        //다음에 나올 이미지 띄우기
                        button1.setImageResource(arr_round4[countRound4 * 2]);
                        button2.setImageResource(arr_round4[countRound4 * 2 + 1]);
                    }

                    countRound4 = countRound4 + 1;
                }

                //결승전
                else if(countRound2<=1){

                }

            }
        });




        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //button1.setImageResource(arr_round16[0]);

                button1.setImageResource(R.drawable.phone);


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



                //일단 기존의 데이터를 10ms간 유지

                if (countRound16<=8){
                    //선택된 이미지를 다음 항목 리스트에 삽입하기
                    arr_round8[countRound16-1] = arr_round16[(countRound16-1)*2+1];
                    System.out.println(countRound16);
                    if (countRound16 ==8){
                        male_round8.setVisibility(View.VISIBLE);
                        male_round16.setVisibility(View.INVISIBLE);

                        System.out.println(arr_round8.length);
                        arr_round8 = GlobalVariables.shuffle(arr_round8);
                        button1.setImageResource(arr_round8[0]);
                        button2.setImageResource(arr_round8[1]);
                        countRound8 = countRound8 + 1;
                    }
                    else {
                        //다음에 나올 이미지 띄우기
                        button1.setImageResource(arr_round16[countRound16 * 2]);
                        button2.setImageResource(arr_round16[countRound16 * 2 + 1]);
                    }

                    countRound16 = countRound16 + 1;
                }

                else if(countRound8<=4){

                    arr_round4[countRound8-1] = arr_round8[(countRound8-1)*2+1];

                    if (countRound8 ==4){
                        male_round4.setVisibility(View.VISIBLE);
                        male_round8.setVisibility(View.INVISIBLE);
                        arr_round4 = GlobalVariables.shuffle(arr_round4);
                        button1.setImageResource(arr_round4[0]);
                        button2.setImageResource(arr_round4[1]);
                        countRound4 = countRound4 + 1;
                    }
                    else {
                        //다음에 나올 이미지 띄우기
                        button1.setImageResource(arr_round8[countRound8 * 2]);
                        button2.setImageResource(arr_round8[countRound8 * 2 + 1]);
                    }

                    countRound8 = countRound8 + 1;
                }

                else if(countRound4<=2){

                    arr_round2[countRound4-1] = arr_round4[(countRound4-1)*2+1];

                    if (countRound4 ==2){
                        male_round2.setVisibility(View.VISIBLE);
                        male_round4.setVisibility(View.INVISIBLE);
                        arr_round2 = GlobalVariables.shuffle(arr_round2);
                        button1.setImageResource(arr_round2[0]);
                        button2.setImageResource(arr_round2[1]);
                        countRound2 = countRound2 + 1;
                    }
                    else {
                        //다음에 나올 이미지 띄우기
                        button1.setImageResource(arr_round4[countRound4 * 2]);
                        button2.setImageResource(arr_round4[countRound4 * 2 + 1]);
                    }

                    countRound4 = countRound4 + 1;
                }

                //결승전
                else if(countRound2<=1){

                }
            }
        });

    }

}