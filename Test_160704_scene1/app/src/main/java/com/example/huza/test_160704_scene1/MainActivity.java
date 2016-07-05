package com.example.huza.test_160704_scene1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = (RelativeLayout) findViewById(R.id.container);
        myView.setOnTouchListener(new RelativeLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                View btn = findViewById(R.id.button);

                Transition tr = new ChangeBounds();
                tr.setDuration(3000);
                tr.setInterpolator(new BounceInterpolator());

                TransitionManager.beginDelayedTransition(myView, tr);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
                        RelativeLayout.TRUE);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
                        RelativeLayout.TRUE);

                btn.setLayoutParams(params);

                ViewGroup.LayoutParams layoutParams = btn.getLayoutParams();

                layoutParams.width = 500;
                layoutParams.height = 350;
                btn.setLayoutParams(layoutParams);



                return false;
            }
        });
    }
}
