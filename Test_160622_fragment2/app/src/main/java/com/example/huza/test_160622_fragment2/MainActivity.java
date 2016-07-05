package com.example.huza.test_160622_fragment2;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements onItemSelectedListner{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListItemSelected(int position) {
        DetailFragment df = (DetailFragment) getFragmentManager().findFragmentById(R.id.frag_detail);

        df.setText(position);
        df.setImage(position);
    }
}
