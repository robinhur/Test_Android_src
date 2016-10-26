package com.example.huza.test_160919_alwaystop;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class AlwaysOnTop extends Service {
    View small_view;
    WindowManager manager;

    public AlwaysOnTop() {
        Log.d("AlwaysOnTop", "서비스 생성");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("AlwaysOnTop", "서비스 시작");

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        small_view = inflater.inflate(R.layout.mini_window, null);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        manager.addView(small_view, params);

        Button btn_close = (Button) small_view.findViewById(R.id.button);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_close_clicked();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (small_view != null) {
            manager.removeView(small_view);
            small_view = null;
        }
        Log.d("AlwaysOnTop", "서비스 소멸");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    public void btn_close_clicked(){
        stopSelf();
    }
}
