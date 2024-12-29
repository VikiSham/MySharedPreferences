package com.example.mysharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    WebView wv;
    Button btnDis;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String user;
    TextView tvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sharedPreferences=getSharedPreferences("myShared", MODE_PRIVATE);
        editor=sharedPreferences.edit();
        user=sharedPreferences.getString("connectedUser","");
        tvUser=findViewById(R.id.tvUser);
        tvUser.setText("Hello "+user+" !!!");
        wv=findViewById(R.id.wv);
        wv.setBackgroundColor(Color.TRANSPARENT);
        wv.loadDataWithBaseURL("file:///android_res/drawable/",
                "<img align='middle' src='androidgif.gif' width='100%' />",
                     "text/html","utf-8", null);
        wv.reload();

        btnDis=findViewById(R.id.btnDis);
        btnDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("connected", false);
                editor.putString("connectedUser", "");
                editor.commit();
                finish();
            }
        });

    }
}