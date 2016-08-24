package com.example.huza.test_160824_calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView montTextView;
    GridView gridView;
    GridViewAdapter gridViewAdapter;
    Calendar mCalendar;

    SimpleDateFormat monthTextView_Format = new SimpleDateFormat("yyyy/MM", Locale.KOREA);
    SimpleDateFormat curYear_Format = new SimpleDateFormat("yyyy", Locale.KOREA);
    SimpleDateFormat curMonth_Format = new SimpleDateFormat("M", Locale.KOREA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        montTextView = (TextView) findViewById(R.id.month_text);
        gridView = (GridView) findViewById(R.id.grid_month);

        mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);

        montTextView.setText(monthTextView_Format.format(mCalendar.getTime()));

        gridViewAdapter = new GridViewAdapter(getApplicationContext(), setMonth(mCalendar), curYear_Format.format(mCalendar.getTime()), curMonth_Format.format(mCalendar.getTime()));
        gridView.setAdapter(gridViewAdapter);

    }

    public void prev_month(View v) {
        mCalendar.add(Calendar.MONTH, -1);

        gridViewAdapter.setcurrentinfo(curYear_Format.format(mCalendar.getTime()), curMonth_Format.format(mCalendar.getTime()));
        montTextView.setText(monthTextView_Format.format(mCalendar.getTime()));

        gridViewAdapter.setmonth(setMonth(mCalendar));
        gridViewAdapter.notifyDataSetChanged();
    }
    public void next_month(View v) {
        mCalendar.add(Calendar.MONTH, +1);

        gridViewAdapter.setcurrentinfo(curYear_Format.format(mCalendar.getTime()), curMonth_Format.format(mCalendar.getTime()));
        montTextView.setText(monthTextView_Format.format(mCalendar.getTime()));

        gridViewAdapter.setmonth(setMonth(mCalendar));
        gridViewAdapter.notifyDataSetChanged();
    }

    public ArrayList<Date_info> setMonth(Calendar curCalendar) {

        ArrayList<Date_info> monthInfo = new ArrayList<Date_info>();

        for (int i = 1; i < curCalendar.get(Calendar.DAY_OF_WEEK); i++) {
            monthInfo.add(new Date_info(0));
        }
        for (int i = 0; i < curCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            monthInfo.add(new Date_info(i+1));
        }

        return monthInfo;
    }
}
