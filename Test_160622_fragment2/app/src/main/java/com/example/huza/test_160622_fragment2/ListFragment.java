package com.example.huza.test_160622_fragment2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by HuZA on 2016-06-22.
 */
public class ListFragment extends android.app.ListFragment{
    onItemSelectedListner listener;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        listener.onListItemSelected(position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setListAdapter(new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                new String[] {"1","2","3","4","5"}
        ));

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (onItemSelectedListner) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
