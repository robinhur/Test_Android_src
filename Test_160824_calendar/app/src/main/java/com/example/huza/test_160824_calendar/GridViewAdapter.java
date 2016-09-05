package com.example.huza.test_160824_calendar;

import android.content.Context;
import android.content.SharedPreferences;
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
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by HuZA on 2016-08-24.
 */
public class GridViewAdapter extends BaseAdapter {

    static final String app_name = "Calendar";

    ArrayList<Date_info> month;
    Context mContext;
    Calendar todayCalendar;
    String cur_month;
    String cur_year;

    HashMap<String, String> schduleHash;

    public GridViewAdapter(Context context, ArrayList<Date_info> month, String cur_year, String cur_month) {
        this.month = month;
        this.mContext = context;
        this.cur_year = cur_year;
        this.cur_month = cur_month;

        todayCalendar = Calendar.getInstance();

        load_hashmap();
    }

    public void load_hashmap(){
        SharedPreferences pref = mContext.getSharedPreferences(app_name, Context.MODE_PRIVATE);

        schduleHash = (HashMap<String, String>)pref.getAll();

        if (schduleHash == null) {
            schduleHash = new HashMap<>();
        }
    }
    public void save_hashmap(){
        SharedPreferences pref = mContext.getSharedPreferences(app_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        for (String key : schduleHash.keySet()) {
            editor.putString(key, schduleHash.get(key));
        }

        editor.commit();
    }

    public void setmonth(ArrayList<Date_info> month){
        this.month = month;
    }
    public void setcurrentinfo(String cur_year, String cur_month) {
        this.cur_year = cur_year;
        this.cur_month = cur_month;
    }

    public String getSchedule(String year, String month, String day) {
        String return_schedule = null;
        String key = year+"-"+month+"-"+day;

        return_schedule = schduleHash.get(key);
        Log.d("Calendar!!", "getSc : " + key + " : " + return_schedule);

        return return_schedule;
    }

    public void setSchedule(String year, String month, String day, String schedule){
        String key = year+"-"+month+"-"+day;

        Log.d("Calendar!!", "setSc : " + key + " : " + schedule);

        schduleHash.put(key, schedule);
        save_hashmap();
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
        TextView sv = (TextView) view.findViewById(R.id.date_schedulView);
        if (month.get(position).getDate()!=0) {
            tv.setText(String.valueOf(month.get(position).getDate()));

            if (getSchedule(cur_year, String.valueOf(Integer.valueOf(cur_month)-1), String.valueOf(month.get(position).getDate())) != null) {
                sv.setText("*");
            }
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
