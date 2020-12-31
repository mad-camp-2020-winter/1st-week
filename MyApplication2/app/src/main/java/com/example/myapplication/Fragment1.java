package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import android.graphics.drawable.Drawable;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String[] NAME;
    private static String[] PHONE;
    private static String[] FINAL_LIST;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int len;


    //json 파일을 스트링으로 읽어오기
    private String getJasonString(){
        String json ="";

        try {
            InputStream is = getActivity().getAssets().open("adress_data.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return json;
    }


    //json 파싱하기
    void doJSONParser() {
        // json 데이터
        StringBuffer sb = new StringBuffer();

        String str = getJasonString();


        try {
            JSONArray jarray = new JSONArray(str);
            len = jarray.length();
            NAME = new String[len];
            PHONE = new String[len];
            for (int i=0;i<len;i++){
                JSONObject jObject = jarray.getJSONObject(i);
                String name = jObject.getString("name");
                String phone = jObject.getString("phone");

                NAME[i] = name;
                PHONE[i] = phone;
                //append("name:"+name+", phone:"+ phone);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    public int findNum(String text){
        for (int i=0; i<10;i++){
            if (text == NAME[i]){
                return i;
            };
        };
        return -1;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, null);
        ListViewAdapter adapter;



        doJSONParser();
        FINAL_LIST = new String[len];

        for (int i =0; i<len; i++){
            FINAL_LIST[i] = NAME[i];
        }
        String[] original = FINAL_LIST;

        //ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, FINAL_LIST);
        adapter = new ListViewAdapter() ;

        //LIST_MENU에 json으로부터 읽은 파일 넣기

        ListView listview = (ListView) view.findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        for (int i=0; i< len; i++){
            adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.human),FINAL_LIST[i]);
        }

        //+ add address 구현하기
        Button button1 = view.findViewById(R.id.button_frag1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),SubActivity.class);
                startActivity(intent);
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                Drawable iconDrawable = item.getIcon() ;

                // TODO : use item data.

                ListViewAdapter adapter;
                adapter = new ListViewAdapter() ;
                listview.setAdapter(adapter);

                FINAL_LIST = original;
                int index = findNum(titleStr);

                if (index>=0) {
                    FINAL_LIST = new String[len+1];
                    for (int i = 0; i < len + 1; i++) {
                        if (i <= index) {
                            FINAL_LIST[i] = NAME[i];
                        } else if (i == index + 1) {
                            FINAL_LIST[i] = PHONE[index];

                        } else {
                            FINAL_LIST[i] = NAME[i - 1];
                        }
                    }
                    for (int i = 0; i<FINAL_LIST.length; i++) {
                        if (i == index+1 && FINAL_LIST.length > len) {
                            adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.phone), FINAL_LIST[i]);
                        }
                        else{
                            adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.human), FINAL_LIST[i]);
                        }
                    }
                }
                else{
                    //titleStr

                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                    }

                    String call_num = "tel:"+ titleStr;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(call_num));

                    try{
                        getContext().startActivity(intent);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                    adapter = new ListViewAdapter() ;
                    listview.setAdapter(adapter);

                    FINAL_LIST = original;
                }
            }
        });
        return view;
    }


}