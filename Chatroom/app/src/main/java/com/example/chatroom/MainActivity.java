package com.example.chatroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        private SharedPreferences pref;
        private SharedPreferences.Editor editor;
        private EditText nameEdit;
        private EditText ipEdit;
        private EditText passwordEdit;
        private EditText portEdit;
        private Button login;
        private CheckBox rememberPass;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            pref = PreferenceManager.getDefaultSharedPreferences(this);
            ipEdit = findViewById(R.id.ip);
            portEdit =  findViewById(R.id.port);
            passwordEdit = findViewById(R.id.password);
            nameEdit = findViewById(R.id.name);
            rememberPass = findViewById(R.id.remember_pass);
            login = findViewById(R.id.login);
            boolean isRemember = pref.getBoolean("remember_password",false);
            if (isRemember) {
                String ip = pref.getString("ip","");
                String port = pref.getString("port","");
                String password = pref.getString("password","");
                ipEdit.setText(ip);
                portEdit.setText(port);
                passwordEdit.setText(password);
                rememberPass.setChecked(true);
            }
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = nameEdit.getText().toString();
                    String ip = ipEdit.getText().toString();
                    String port = portEdit.getText().toString();
                    String password = passwordEdit.getText().toString();
                    if ( ip.equals("192.168.1.41") && port.equals("12345") && password.equals("207103"))
                    {
                        editor = pref.edit();
                        if (rememberPass.isChecked()) {
                            editor.putBoolean("remember_password",true);
                            editor.putString("ip",ip);
                            editor.putString("port",port);
                            editor.putString("password",password);
                        }else  {
                            editor.clear();
                        }
                        editor.apply();
                        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                        intent.putExtra("name",name);
                        intent.putExtra("ip",ip);
                        intent.putExtra("port",port);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this,"信息有误，请重新输入密码",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
}
