package com.example.huza.test_160629_event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements customTouchInterface {

    Button btn;
    TextView tv;
    GestureDetector gd;
    AnotherView av;
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textView);

        btn.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return false;
            }
        });

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gd.onTouchEvent(event);
                /*if (event.getAction() == MotionEvent.ACTION_UP)
                    tv.setText("ACTION_UP");
                else if (event.getAction() == MotionEvent.ACTION_DOWN)
                    tv.setText("ACTION_DOWN");
                return true;*/
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("onClick");
            }
        });

        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tv.setText("onLongClick");
                return false;
            }
        });

        gd = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                tv.setText("onScroll : " + distanceX + " + " + distanceY);
                return false;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                tv.setText("onFling : " + velocityX + " + " + velocityY);
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (gd != null)
            return gd.onTouchEvent(event);
        else
            return super.onTouchEvent(event);

    }

    @Override
    public boolean customTouchListener(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            tv.setText("ACTION_DOWN");
        }
        return false;
    }
}
