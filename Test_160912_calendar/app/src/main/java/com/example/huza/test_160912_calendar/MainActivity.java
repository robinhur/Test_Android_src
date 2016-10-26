package com.example.huza.test_160912_calendar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView montTextView;
    GridView gridView;
    GridViewAdapter gridViewAdapter;
    Calendar mCalendar;
    Calendar today;

    String selected_day;
    int selected_day_position;
    Button btn_today;
    EditText schedule_text;

    SimpleDateFormat monthTextView_Format = new SimpleDateFormat("yyyy/MM", Locale.KOREA);
    SimpleDateFormat curYear_Format = new SimpleDateFormat("yyyy", Locale.KOREA);
    SimpleDateFormat curMonth_Format = new SimpleDateFormat("M", Locale.KOREA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        montTextView = (TextView) findViewById(R.id.month_text);
        gridView = (GridView) findViewById(R.id.grid_month);

        today = Calendar.getInstance();
        mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);

        selected_day_position = -1;
        montTextView.setText(monthTextView_Format.format(mCalendar.getTime()));
        btn_today = (Button) findViewById(R.id.today_month);
        schedule_text = (EditText) findViewById(R.id.scheduleET);

        gridViewAdapter = new GridViewAdapter(getApplicationContext(), setMonth(mCalendar), curYear_Format.format(mCalendar.getTime()), curMonth_Format.format(mCalendar.getTime()));
        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String day = ((TextView)view.findViewById(R.id.date_textView)).getText().toString();
                Log.d("Calendar!!", day);

                schedule_text.setText("");

                if (selected_day == day){
                    //해제
                    parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                    selected_day = "";
                    selected_day_position = -1;
                } else {
                    if (selected_day_position != -1)
                        parent.getChildAt(selected_day_position).setBackgroundColor(Color.WHITE);

                    parent.getChildAt(position).setBackgroundColor(Color.GREEN);

                    selected_day = day;
                    selected_day_position = position;
                    load_schedule();
                }
            }
        });

        gridView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                String day = ((TextView)v.findViewById(R.id.date_textView)).getText().toString();
                TextView sv = (TextView) v.findViewById(R.id.date_schedulView);
                if (sv.getText() == "*") {
                    gridViewAdapter.setSchedule(
                            String.valueOf(mCalendar.get(Calendar.YEAR)),String.valueOf(mCalendar.get(Calendar.MONTH)), day, null
                    );

                    Toast.makeText(getApplicationContext(), "일정이 삭제 되었습니다", Toast.LENGTH_LONG).show();

                    sv.setText("");
                    gridViewAdapter.notifyDataSetChanged();
                }

                return false;
            }
        });

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
    public void today_month(View v) {
        mCalendar.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 1);

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

        if (mCalendar.get(Calendar.YEAR)==today.get(Calendar.YEAR) && mCalendar.get(Calendar.MONTH)==today.get(Calendar.MONTH))
            btn_today.setEnabled(false);
        else
            btn_today.setEnabled(true);

        selected_day = "";
        selected_day_position = -1;

        return monthInfo;
    }

    public void insert_schedule(View v) {
        String schedule = schedule_text.getText().toString();

        gridViewAdapter.setSchedule
                (String.valueOf(mCalendar.get(Calendar.YEAR)),String.valueOf(mCalendar.get(Calendar.MONTH)), selected_day, schedule);

        Toast.makeText(getApplicationContext(), "일정이 입력되었습니다", Toast.LENGTH_LONG).show();

        gridViewAdapter.notifyDataSetChanged();
    }

    public void load_schedule() {
        String schdule = gridViewAdapter.getSchedule(String.valueOf(mCalendar.get(Calendar.YEAR)),String.valueOf(mCalendar.get(Calendar.MONTH)), selected_day);
        schedule_text.setText("");

        if (schdule != null) {
            schedule_text.setText(schdule);
        }
    }
}
