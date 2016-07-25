package com.example.huza.test_160718_cardview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HuZA on 2016-07-18.
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.myViewHolder> {

    String[] title_arr = {"Chapter One", "Chapter Two", "Chapter Three", "Chapter One", "Chapter Two", "Chapter Three", "Chapter One", "Chapter Two", "Chapter Three", "Chapter One", "Chapter Two", "Chapter Three", "Chapter Four"};
    String[] detail_arr = {"111","222","333","111","222","333","111","222","333","111","222","333","444"};

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        myViewHolder viewHolder = new myViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.title.setText(title_arr[position]);
        holder.detail.setText(detail_arr[position]);
    }

    @Override
    public int getItemCount() {
        return title_arr.length;
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView title;
        public TextView detail;

        public myViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            title = (TextView) itemView.findViewById(R.id.title_tv);
            detail = (TextView) itemView.findViewById(R.id.detail_tv);
        }
    }

}
