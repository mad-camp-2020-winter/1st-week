package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

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
    private static final String[] NAME={"0","0","0","0","0","0","0","0","0","0"};
    private static final String[] PHONE = {"0","0","0","0","0","0","0","0","0","0"};
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


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


    void doJSONParser() {
        // json 데이터
        StringBuffer sb = new StringBuffer();

        String str = getJasonString();

        try {
            JSONArray jarray = new JSONArray(str);
            for (int i=0;i<jarray.length();i++){
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
        doJSONParser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, null);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, NAME);
        //LIST_MENU에 json으로부터 읽은 파일 넣기

        ListView listview = (ListView) view.findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        return view;
    }





}