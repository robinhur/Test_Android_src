package com.example.huza.test_160823_calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GridView monthView;
    CalendarMonthAdapter monthViewAdapter;

    TextView monthText;

    int curYear;
    int curMonth;

    int curPosition;
    EditText scheduleInput;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
