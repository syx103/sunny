package com.example.text5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Log.e("FirstActivity","First_onCreate");
        Button button1 = (Button) findViewById(R.id.Start_r);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("FirstActivity","First_onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("FirstActivity","First_onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("FirstActivity","first_onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("FirstActivity","first_onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("SecondActivity","first_onDestroy");
    }
}
