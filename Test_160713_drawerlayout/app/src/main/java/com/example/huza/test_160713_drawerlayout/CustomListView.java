package com.example.huza.test_160713_drawerlayout;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by HuZA on 2016-07-13.
 */
public class CustomListView extends LinearLayout {
    TextView  name;
    TextView  price;

    public CustomListView(Context context) {
        super(context);
        init();
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_listview_layout, this, true);

        name = (TextView) findViewById(R.id.main_tv);
        name.setTextColor(Color.BLACK);
        price = (TextView) findViewById(R.id.sub_tv);
        price.setTextColor(Color.BLACK);
    }

    public String getPrice() {
        return price.getText().toString();
    }

    public void setPrice(String price) {
        this.price.setText(price);
    }

    public String getName() {
        return name.getText().toString();
    }

    public void setName(String name) {
        this.name.setText(name);
    }
}
