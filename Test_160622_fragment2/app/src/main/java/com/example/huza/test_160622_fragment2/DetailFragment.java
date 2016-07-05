package com.example.huza.test_160622_fragment2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HuZA on 2016-06-22.
 */
public class DetailFragment extends Fragment {

    String[] number = new String[] {"One", "Two", "Three", "Four", "Five"};
    int[] imgpath = {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_frag_layout, container, false);
    }

    public void setText(int position) {
        TextView tv = (TextView) getView().findViewById(R.id.textView);
        tv.setText(number[position]);
    }

    public void setImage(int position) {
        ImageView iv = (ImageView) getView().findViewById(R.id.imageView);
        iv.setImageResource(imgpath[position]);
    }
}
