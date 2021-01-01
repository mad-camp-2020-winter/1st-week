package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import androidx.fragment.app.Fragment;

public class Fragment2Bookmarks extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    MainActivity activity;

    public Fragment2Bookmarks() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2Bookmarks newInstance(String param1, String param2) {
        Fragment2Bookmarks fragment = new Fragment2Bookmarks();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2_bookmarks, container, false);

        //갤러리 gridView 선언
        GridView gridView = (GridView) view.findViewById(R.id.gridView2);
        ListAdapter adapter = new Fragment2Bookmarks.ImageAdapter(this.getContext());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
//                activity.onFragmentChange(1);
//                activity.addFragment2Detail();
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity(), Fragment2Activity.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });

        //홈 누를시 원래 갤러리로 이동
        final View menuHomeView = view.findViewById(R.id.menu_home);
        menuHomeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(0);
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity)getActivity();
    }

    @Override
    public void onDetach(){
        super.onDetach();
        activity = null;
    }


    //gridView 사용 위한 ImageAdapter
    public class ImageAdapter extends BaseAdapter {
        private Context context;

        private Integer[] images = GlobalVariables.bookMarks.toArray(new Integer[GlobalVariables.bookMarks.size()]);

        public ImageAdapter(Context con){
            this.context = con;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return images[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            Point winSize = new Point();
            getActivity().getWindowManager().getDefaultDisplay().getSize(winSize);
            int width = winSize.x;
            int height = winSize.y;
            width = width / 3 - 10;
            height = height / 5 - 20;

            if(convertView==null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(width,height));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(5,5,5,5);
            }
            else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(images[position]);
            return imageView;
        }
    }
}
