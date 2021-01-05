package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Address_revise extends AppCompatActivity {



    //name.setText("")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_revise);


        Intent message = getIntent();
        int position = message.getIntExtra("index",0);
        String prev_name = message.getStringExtra("name");
        String prev_phone = message.getStringExtra("phone");
        int index3 = message.getIntExtra("index3",0);

        EditText name = findViewById(R.id.edit_address_name);
        EditText phone = findViewById(R.id.edit_address_phone);

        name.setText(prev_name);
        phone.setText(prev_phone);

        Button button1 = (Button)findViewById(R.id.cancel_button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Address_revise.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button)findViewById(R.id.save_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //name.getText()
                //phone.getText()

                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(getFilesDir() + "address.txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                String result = "";
                String dummy = "";
                String str_input = null;
                int line_num = 0;
                while (true) {
                    line_num = line_num +1;
                    try {
                        if ((str_input = br.readLine()) != null){
                            if (line_num==(index3*4+3)){
                                dummy = dummy + str_input;
                                result = result +
                                        "    \"name\":\""+name.getText().toString()+"\",\n" +
                                        "    \"phone\":\""+phone.getText().toString()+"\"\n" ;
 ;
                            }
                            else if((line_num>(index3*4+3)) && line_num <=(index3*4+4)){
                                dummy = dummy + str_input;
                            }
                            else{
                                 result += str_input + "\n";
                            }
                        }
                        else{
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {

                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(result);

                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter(getFilesDir() + "address.txt", false));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bw.write(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(Address_revise.this,MainActivity.class);
                startActivity(intent);
            }

        });


    }
}