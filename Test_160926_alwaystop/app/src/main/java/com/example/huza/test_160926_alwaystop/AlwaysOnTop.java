package com.example.huza.test_160926_alwaystop;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class AlwaysOnTop extends Service {
    View small_view;
    TextView timer_text;
    TextView end_time_text;
    TextView now_time_text;
    WindowManager manager;

    SimpleDateFormat end_time_format = new SimpleDateFormat("hh : mm : ss", Locale.KOREA);
    Calendar end_time;
    int time;

    PowerManager.WakeLock wakeLock;
    Timer timer;
    TimerTask second;
    Handler handler = new Handler();

    public AlwaysOnTop() {
        Log.d("AlwaysOnTop", "서비스 생성");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("AlwaysOnTop", "서비스 onStartCommand 시작");

        String time_string = intent.getStringExtra("Time");
        time = Integer.valueOf(time_string);
        Log.d("AlwaysOnTop", "time_string : " + time_string);

        end_time = Calendar.getInstance();
        end_time.add(Calendar.SECOND, time);
        end_time_text.setText(end_time_format.format(end_time.getTime()));
        Log.d("AlwaysOnTop", "end_time : " + end_time.get(Calendar.HOUR) + " : " + end_time.get(Calendar.MINUTE) + " : " + end_time.get(Calendar.SECOND));

        timer_start();

        return startId;
    }

    private void timer_start() {

        Log.d("AlwaysOnTop", "스레드 시작");

        second = new TimerTask() {
            @Override
            public void run() {
                Log.d("AlwaysOnTop", "남은 시간 : " + time);
                textview_update();
            }
        };

        timer = new Timer();
        timer.schedule(second, 0, 1000);

    }

    private void textview_update() {
        Runnable updater = new Runnable() {
            @Override
            public void run() {
                timer_text.setText(time + "초 남음");
                time--;
                Calendar now_calendar = Calendar.getInstance();
                now_time_text.setText(end_time_format.format(now_calendar.getTime()));
            }
        };
        if (time != 0) {
            handler.post(updater);
        } else {
            btn_close_clicked();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("AlwaysOnTop", "서비스 시작");

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        small_view = inflater.inflate(R.layout.mini_window, null);
        timer_text = (TextView) small_view.findViewById(R.id.tv_timer);
        end_time_text = (TextView) small_view.findViewById(R.id.tv_end_time);
        now_time_text = (TextView) small_view.findViewById(R.id.tv_now_time);

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

            timer.cancel();
            Log.d("AlwaysOnTop", "스레드 소멸");
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
