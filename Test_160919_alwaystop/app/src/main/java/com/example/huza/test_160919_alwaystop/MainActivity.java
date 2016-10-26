package com.example.huza.test_160919_alwaystop;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.fromParts("package", getPackageName(), null);
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri);

        if (!hasWindowOverlayPermission(getApplicationContext())){
            Log.d("AlwaysOnTop", "권한 없음");
            startActivity(intent);
        } else {
            Log.d("AlwaysOnTop", "권한 있음");
        }
    }

    public static boolean hasWindowOverlayPermission(Context context) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(context)) {
                return false;
            }
            return true;
        }

        return false;

    }

    public void btn_stop_clicked(View v) {
        Log.d("AlwaysOnTop", "서비스 종료 클릭");
        stopService(new Intent(this, AlwaysOnTop.class));
        Log.d("AlwaysOnTop", "서비스 종료 클릭 끝");
    }
    public void btn_start_clicked(View v) {

        Log.d("AlwaysOnTop", "서비스 버튼 클릭");

        startService(new Intent(this, AlwaysOnTop.class));
        Log.d("AlwaysOnTop", "서비스 버튼 클릭 끝");
    }

}
