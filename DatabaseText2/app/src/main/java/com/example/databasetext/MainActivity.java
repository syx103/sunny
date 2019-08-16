package com.example.databasetext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private String newId;
//    private MyDatabaseHelper dpHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*dpHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);
        final Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpHelper.getWritableDatabase();
            }
        });*/
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SQLiteDatabase db = dpHelper.getWritableDatabase();
                Uri uri = Uri.parse("content://com.example.databasetext.provider/book");
                ContentValues values = new ContentValues();
                values.put("name","The Da Vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",454);
                values.put("price",16.96);
                Uri newUri = getContentResolver().insert(uri,values);
                newId = newUri .getPathSegments().get(1);
                /*db.insert("Book",null,values);
                values.clear();
                values.put("name","The Lost Symbol");
                values.put("author","Dan Brown");
                values.put("pages",510);
                values.put("price",19.95);
                db.insert("Book",null,values);*/
            }
        });
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              SQLiteDatabase db = dpHelper.getWritableDatabase();
                Uri uri = Uri.parse("content://com.example.databasetext.provider/book" + newId);
                ContentValues values = new ContentValues();
                values.put("price",10.99);
                values.put("pages",1216);
                values.put("name","A Storm of Swords");
                getContentResolver().update(uri,values,null,null);
//              db.update("Book",values,"name = ?",new String[] {"The Da Vinci Code"});
            }
        });
        Button queryButton = findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SQLiteDatabase db = dpHelper.getWritableDatabase();
                Uri uri = Uri.parse("content://com.example.databasetext.provider/book");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if(cursor.moveToFirst()) {
                    do  {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.e("MainActivity","book name is " + name);
                        Log.e("MainActivity","book author is " + author);
                        Log.e("MainActivity","book pages is " + pages);
                        Log.e("MainActivity","book price is " + price);
                    }while(cursor.moveToNext());
                }
                cursor.close();
            }
        });
        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.example.databasetext.provider/book" + newId);
                getContentResolver().delete(uri,null,null);
            }
        });
    }
}
