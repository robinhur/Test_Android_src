package com.example.huza.test_160704_actionbar;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View actionbar_layout = getLayoutInflater().inflate(R.layout.actionbar_layout, null);
        ActionBar ab = getSupportActionBar();
        ab.setCustomView(actionbar_layout);
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM|ActionBar.DISPLAY_SHOW_TITLE|ActionBar.DISPLAY_HOME_AS_UP);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btn_clicked(View v) {
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.container);

        switch(v.getId()) {
            case R.id.btn_next :
                rl.setBackgroundColor(Color.YELLOW);
                break;
            case R.id.btn_prev :
                rl.setBackgroundColor(Color.GRAY);
                break;
        }
    }
}
