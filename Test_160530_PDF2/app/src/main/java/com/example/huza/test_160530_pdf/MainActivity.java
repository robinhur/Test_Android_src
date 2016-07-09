package com.example.huza.test_160530_pdf;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.editText);
        Button btn = (Button) findViewById(R.id.btn_openClicked);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filepath = editText.getText().toString();
                if (filepath.length() > 0) {
                    openPDF(filepath);
                }
            }
        });
    }

    private void openPDF(String filepath) {
        File file = new File(filepath);

        if (file.exists()) {
            Uri uri = Uri.fromFile(file);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            //intent.setDataAndType(uri, "application/pdf");

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this, "PDF 뷰어가 없습니다", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "파일이 없습니다",Toast.LENGTH_LONG).show();
        }
    }
}
