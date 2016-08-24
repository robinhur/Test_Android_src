package com.example.huza.test_160824_calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by HuZA on 2016-08-24.
 */
public class GridViewAdapter extends BaseAdapter {

    ArrayList<Date_info> month;
    Context mContext;
    Calendar todayCalendar;
    String cur_month;
    String cur_year;

    public GridViewAdapter(Context context, ArrayList<Date_info> month, String cur_year, String cur_month) {
        this.month = month;
        this.mContext = context;
        this.cur_year = cur_year;
        this.cur_month = cur_month;

        todayCalendar = Calendar.getInstance();
    }

    public void setmonth(ArrayList<Date_info> month){
        this.month = month;
    }
    public void setcurrentinfo(String cur_year, String cur_month) {
        this.cur_year = cur_year;
        this.cur_month = cur_month;
    }

    @Override
    public int getCount() {
        return month.size();
    }

    @Override
    public Date_info getItem(int position) {
        return month.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.date_layout, parent, false);


        TextView tv = (TextView) view.findViewById(R.id.date_textView);
        if (month.get(position).getDate()!=0) {
            tv.setText(String.valueOf(month.get(position).getDate()));
        }

        Log.d("Test_calendar", "cur_month : " + cur_month + " = " + String.valueOf(todayCalendar.get(Calendar.MONTH) + 1) +"cur_year : " + cur_year + " = " + String.valueOf(todayCalendar.get(Calendar.YEAR)) + "tv.getText() : " + tv.getText().toString() + " = " + String.valueOf(todayCalendar.get(Calendar.DAY_OF_MONTH)));

        if ((cur_month.equals(String.valueOf(todayCalendar.get(Calendar.MONTH) + 1)))&&(cur_year.equals(String.valueOf(todayCalendar.get(Calendar.YEAR))))&&(tv.getText().equals(String.valueOf(todayCalendar.get(Calendar.DAY_OF_MONTH)))))
            tv.setTextColor(Color.MAGENTA);
        else {
            tv.setTextColor(Color.BLACK);
        }

        return view;
    }
}
