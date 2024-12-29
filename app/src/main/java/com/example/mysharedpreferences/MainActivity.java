package com.example.mysharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean connected=false;
    String user="", pass="";
    EditText etUser,etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences("myShared", MODE_PRIVATE);
        editor=sharedPreferences.edit();
        // read from shared preferences
        connected=sharedPreferences.getBoolean("connected",false);
        if(connected)
        {
            Intent go = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(go);
        }

        etUser=findViewById(R.id.etUser);
        etPass=findViewById(R.id.etPass);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=etUser.getText().toString();
                pass=etPass.getText().toString();
                if(!user.equals("") && !pass.equals("")) {
                    // write to shared preferences
                    if (!connected) {
                        editor = sharedPreferences.edit();
                        editor.putBoolean("connected", true);
                        editor.putString("connectedUser", user);
                        editor.commit();
                        Intent go = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(go);
                    }
                }
                else
                    Toast.makeText(MainActivity.this, "Need to fill data!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}