package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2Detail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //추가 변수
    private int index;
    private Integer[] images = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9};
    private Integer[] thumbButton = {R.id.thumb_button_1, R.id.thumb_button_2, R.id.thumb_button_3, R.id.thumb_button_4, R.id.thumb_button_5, R.id.thumb_button_6, R.id.thumb_button_7, R.id.thumb_button_8, R.id.thumb_button_9};

    public Fragment2Detail(int index) {
        this.index = index;
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
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
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

//        zoomImageFromThumb(R.drawable.a1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2_detail, container, false);


        //View Click -> Big Image 불러옴
        final View thumb1View = view.findViewById(R.id.thumb_button_1);
        thumb1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(R.drawable.a1);
            }
        });

        final View thumb2View = view.findViewById(R.id.thumb_button_2);
        thumb2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(R.drawable.a2);
            }
        });

        final View thumb3View = view.findViewById(R.id.thumb_button_3);
        thumb3View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(R.drawable.a3);
            }
        });

        final View thumb4View = view.findViewById(R.id.thumb_button_4);
        thumb4View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(R.drawable.a4);
            }
        });

        final View thumb5View = view.findViewById(R.id.thumb_button_5);
        thumb5View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(R.drawable.a5);
            }
        });

        final View thumb6View = view.findViewById(R.id.thumb_button_6);
        thumb6View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(R.drawable.a6);
            }
        });

        final View thumb7View = view.findViewById(R.id.thumb_button_7);
        thumb7View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(R.drawable.a7);
            }
        });

        final View thumb8View = view.findViewById(R.id.thumb_button_8);
        thumb8View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(R.drawable.a8);
            }
        });

        final View thumb9View = view.findViewById(R.id.thumb_button_9);
        thumb9View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(R.drawable.a9);
            }
        });

        //Fragment2(이전 프래그먼트)에서 누른 이미지 확장
//        final View thumbPickedView = view.findViewById(R.id.thumb_button_1);
        return view;
    }

    public void zoomImageFromThumb(int imageResId) {
        final ImageView expandedImageView = (ImageView) getView().findViewById(R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);

        expandedImageView.setVisibility(View.VISIBLE);
    }


}
