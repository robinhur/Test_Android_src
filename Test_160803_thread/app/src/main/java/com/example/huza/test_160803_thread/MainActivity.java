package com.example.huza.test_160803_thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String text = bundle.getString("status");

            TextView tv = (TextView) findViewById(R.id.tv);
            tv.setText(text);
        }
    };

    public void btn_clicked(View v) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Message msg = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("status","START");
                msg.setData(bundle);
                handler.sendMessage(msg);

                for (int i = 0; i < 50000; i++) {
                    int cnt = 0;
                    for (int j = 0; j < i; j++) {
                        cnt++;
                    }
                }

                Message msg2 = handler.obtainMessage();
                Bundle bundle2 = new Bundle();
                bundle2.putString("status","DONE");
                msg2.setData(bundle2);
                handler.sendMessage(msg2);

            }
        };

        Thread myT = new Thread(runnable);
        myT.start();
    }
}
