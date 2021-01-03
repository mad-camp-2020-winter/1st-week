package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class BookmarksVPAdapter extends PagerAdapter {

    private Context mContext = null;
    private int primary;
    private Integer[] items = GlobalVariables.bookMarks.toArray(new Integer[GlobalVariables.bookMarks.size()]);

    public BookmarksVPAdapter(){
    }

    // Context를 전달받아 mContext에 저장하는 생성자 추가.
    public BookmarksVPAdapter(Context context, int primary) {
        mContext = context ;
        this.primary = primary;
    }

    private View mCurrentView;

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentView = (View)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null ;

        if (mContext != null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_2_bookmarks_pager, container, false);

            ImageView imageView = (ImageView) view.findViewById(R.id.expanded_image_pager) ;
//            imageView.bringToFront();
            imageView.setImageResource(items[position]);
//            imageView.setImageResource(R.drawable.a1);
        }

        // 뷰페이저에 추가.
        container.addView(view) ;

        return view ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 뷰페이저에서 삭제.
//        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (View) object;
    }
}

