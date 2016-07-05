package com.example.huza.test_160704_slidingpanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btn;
    LinearLayout ll;

    Animation move_left;
    Animation move_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = (LinearLayout) findViewById(R.id.sub_slide);
        btn = (Button) findViewById(R.id.button);

        move_left = AnimationUtils.loadAnimation(this, R.anim.move_left);
        move_right = AnimationUtils.loadAnimation(this, R.anim.move_right);

        move_right.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btn.setText("열기");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        move_left.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btn.setText("닫기");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void btn_clicked(View v) {
        if (btn.getText().toString()=="열기") {
            ll.setVisibility(View.VISIBLE);
            ll.startAnimation(move_left);
        } else {
            ll.startAnimation(move_right);
            ll.setVisibility(View.GONE);
        }
    }
}
