package com.example.huza.test_160622_fragment1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean whichFragment = true;
    Fragment menu = new MenuFragment();
    Fragment first = new FirstFragment();
    Fragment second = new SecondFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.leftFragment, menu, "MENU_FRAG");
        if (findViewById(R.id.rightFragment) != null) {
            ft.add(R.id.rightFragment, first, "FIRST_FRAG");
        }
        ft.commit();
    }

    public void btn_clicked(View v) {
        if (findViewById(R.id.rightFragment) != null) {
            Fragment fr;
            String TAG;
            if (whichFragment) {
                fr = second;
                TAG = "SECOND_FRAG";
            } else {
                fr = first;
                TAG = "FIRST_FRAG";
            }

            whichFragment = !whichFragment;
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ft.replace(R.id.rightFragment, fr, TAG);

            ft.commit();
        } else {
            Toast.makeText(MainActivity.this, "없음", Toast.LENGTH_SHORT).show();
        }
    }
}
