package com.example.databasetext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dpHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dpHelper = new MyDatabaseHelper(this,"BookStore.dp",null,1);
        Button createdatabase = (Button) findViewById(R.id.create_database);
        createdatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpHelper.getWritableDatabase();
            }
        });
    }
}
