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
    private Integer[] arr_round8 = male;
    private Integer[] arr_round4 = new Integer[8];
    private Integer[] arr_round2 = new Integer[4];
    private Integer[] arr_round1 = new Integer[2];

    int countRound8 = 0;
    int countRound4 = 0;
    int countRound2 = 0;
    int countRound1 = 0;

    Integer[] test = {1,2,3,4,5,6,7,8,9,10};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3_male);

        final Integer[] image_round8 = GlobalVariables.shuffle(GlobalVariables.images);

        final TextView male_round8 = findViewById(R.id.male_round8);
        final TextView male_round4 = findViewById(R.id.male_round4);
        final TextView male_round2 = findViewById(R.id.male_round2);
        final TextView male_round1 = findViewById(R.id.male_round1);

        male_round4.setVisibility(View.INVISIBLE);
        male_round2.setVisibility(View.INVISIBLE);
        male_round1.setVisibility(View.INVISIBLE);

        ImageButton button1 = findViewById(R.id.male_first_button);
        ImageButton button2 = findViewById(R.id.male_second_button);

        button1.setImageResource(arr_round8[0]);
        button2.setImageResource(arr_round8[1]);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}