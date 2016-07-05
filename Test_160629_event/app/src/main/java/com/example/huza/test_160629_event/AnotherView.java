package com.example.huza.test_160629_event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by HuZA on 2016-06-29.
 */
public class AnotherView extends View {

    customTouchInterface tl;

    public AnotherView(Context context) {
        super(context);
        tl = (customTouchInterface) context;
    }

    public AnotherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        tl = (customTouchInterface) context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tl.customTouchListener(event);
        return super.onTouchEvent(event);
    }
}
