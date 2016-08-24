package com.example.huza.test_160823_calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by HuZA on 2016-08-23.
 */
public class MonthItemView extends TextView {

    private MonthItem item;

    public MonthItemView(Context context) {
        super(context);

        init();
    }

    public MonthItemView(Context context, AttributeSet attrs) {
        super(context);

        init();
    }

    public void init() {
        setBackgroundColor(Color.WHITE);
    }

    public MonthItem getItem() {
        return item;
    }

    public void setItem(MonthItem item) {
        this.item = item;

        int day = item.getDay();
        if (day != 0) {
            setText(String.valueOf(day));
        } else {
            setText("");
        }
    }

}
