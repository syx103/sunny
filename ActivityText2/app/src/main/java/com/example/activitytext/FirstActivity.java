package com.example.activitytext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FirstAcitivity","Task id is " + getTaskId());
        setContentView(R.layout.first_layout);
        Button button1 = (Button)findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(FirstActivity.this,FirstActivity.class);
               Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
               startActivity(intent);
            }
        });
        //setContentView(R.layout.first_layout);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item: {
                Toast.makeText(this, "You click Add", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.remove_item: {
                Toast.makeText(this, "You click Remove", Toast.LENGTH_SHORT).show();
                break;
            }
            default:
        }
        return true;
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.d("FirstActivity","onRestart");
//    }
}
