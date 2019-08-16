package com.example.text5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Log.e("SecondActivity","Second_onCreate");
        Button button2 = (Button) findViewById(R.id.End_d);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("nonono");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("SecondActivity","second_onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("SecondActivity","second_onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("SecondActivity","second_onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("SecondActivity","second_onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("SecondActivity","second_onDestroy");
    }
}
