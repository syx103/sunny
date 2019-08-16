package com.example.chatroom2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText input_name;
    private EditText ip_address;
    private EditText port_name;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        input_name = findViewById(R.id.input_name);
        ip_address = findViewById(R.id.ip_address);
        ip_address.setText("192.168.1.41");
        port_name = findViewById(R.id.port_name);
        port_name.setText("12345");
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = input_name.getText().toString();
                String ip = ip_address.getText().toString();
                String port = port_name.getText().toString();
                Intent intent = new Intent(MainActivity.this,Second.class);
                intent.putExtra("name",name);
                intent.putExtra("ip",ip);
                intent.putExtra("port",port);
                startActivity(intent);
            }
        });
    }
}
