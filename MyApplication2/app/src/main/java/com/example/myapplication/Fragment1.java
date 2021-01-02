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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int len;
    private String search_text;
    private int stringInSearchText = 0;
    private int adapterMode = 0;
    //private ArrayAdapter<String> adapter;
    //private ArrayList<String> final_list;

    ListViewAdapter adapter;
    ListViewAdapter adapter2;

    //private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;


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
        for (int i=0; i<NAME.length ;i++){
            if (text == NAME[i]){
                return i;
            };
        };
        return -1;
    }

    public int find_index(String text){
                for (int i = 0 ; i<adapter.getCount() ;i++){
                    if (text == adapter.getObjString(i)){
                        return i;
                    }
                }
                return -1;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, null);

        adapter = new ListViewAdapter() ;
        adapter2 = new ListViewAdapter();

        //final_list 초기화

        //final_list = new ArrayList<String>();

        ListView listview = (ListView) view.findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        doJSONParser();

        for (int i =0; i<len; i++) {
            adapter.addItemLast(ContextCompat.getDrawable(getActivity(), R.drawable.human), NAME[i]);
        }


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);

                String titleStr = item.getTitle();
                Drawable iconDrawable = item.getIcon();

                // TODO : use titleStr data.
                int index = findNum(titleStr); //index는 name에 해당하는 번호 찾는거
                int index2 = find_index(titleStr); //index2는 final_list에서 어디에 위치하는지 찾는거



                if (adapterMode == 0) {
                    if (index >= 0 && (index2 + 1) == adapter.getCount()) {
                        adapter.addItemLast(ContextCompat.getDrawable(getActivity(), R.drawable.phone), PHONE[index]);
                        adapter.notifyDataSetChanged();
                    } else if (index >= 0 && findNum(adapter.getObjString(index2 + 1)) >= 0) { //아래에 숫자를 나오게 해야할 때
                        adapter.addItemIndex(index2 + 1, ContextCompat.getDrawable(getActivity(), R.drawable.phone), PHONE[index]);
                        adapter.notifyDataSetChanged();
                    } else if (index >= 0 && findNum(adapter.getObjString(index2 + 1)) < 0) { //아래에 번호 나와있는데 이름 또 눌렀을 때
                        adapter.removeItem(index2 + 1);
                        adapter.notifyDataSetChanged();
                    } else if (index < 0) { //숫자를 눌렀을 때
                        //titleStr


                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                        }

                        String call_num = "tel:" + titleStr;
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(call_num));

                        try {
                            getContext().startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                else if (adapterMode == 1){
                    if (index >= 0 && position+1 == adapter2.getCount()) { //하나 밖에 없을 때 숫자 나오게 하기
                        adapter2.addItemLast(ContextCompat.getDrawable(getActivity(), R.drawable.phone), PHONE[index]);
                        adapter2.notifyDataSetChanged();
                    }
                    // 이름 누르면 번호 들어가게 하기
                    else if( index >= 0 && findNum(adapter2.getObjString(position+1)) < 0){
                        adapter2.removeItem(position + 1);
                        adapter2.notifyDataSetChanged();
                    }
                    //여기서부턴 여러개의 데이터가 나왔을 때
                    else if (index >= 0 && findNum(adapter2.getObjString(index2 + 1)) >= 0) { //아래에 숫자를 나오게 해야할 때
                        adapter2.addItemIndex(index2 + 1, ContextCompat.getDrawable(getActivity(), R.drawable.phone), PHONE[index]);
                        adapter2.notifyDataSetChanged();
                    } else if (index >= 0 && findNum(adapter2.getObjString(index2 + 1)) < 0) { //아래에 번호 나와있는데 이름 또 눌렀을 때
                        adapter2.removeItem(index2 + 1);
                        adapter2.notifyDataSetChanged();
                    } else if (index < 0) { //숫자를 눌렀을 때
                        //titleStr

                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                        }

                        String call_num = "tel:" + titleStr;
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(call_num));

                        try {
                            getContext().startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


        });

        //+ add address 구현하기
        Button button1 = view.findViewById(R.id.button_frag1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),SubActivity.class);
                startActivity(intent);
            }
        });


        //search 후 찾는 기능 구현하기
        Button button2 = view.findViewById(R.id.enter);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringInSearchText = 0;
                //1. edit text 가져오기
                EditText Search = getActivity().findViewById(R.id.search);
                search_text = Search.getText().toString();
                adapter2.clearAdapter();

                if (search_text.length() == 0){
                    adapterMode = 0;
                    listview.setAdapter(adapter);
                }
                else {
                    //2. listview에서 찾기
                    adapterMode = 1;
                    listview.setAdapter(adapter2);
                    for (int i = 0; i < NAME.length; i++) {
                        if (NAME[i].toLowerCase().contains(search_text.toLowerCase()) || PHONE[i].contains(search_text)) {
                            System.out.println();
                            adapter2.addItemLast(ContextCompat.getDrawable(getActivity(), R.drawable.human), NAME[i]);
                            stringInSearchText = stringInSearchText + 1;
                        }
                    }
                    if (stringInSearchText == 0) {
                        adapter2.clearAdapter();
                    }

                }
            }
        });
        return view;
    }

}