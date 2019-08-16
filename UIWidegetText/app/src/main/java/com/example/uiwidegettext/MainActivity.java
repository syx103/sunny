package com.example.uiwidegettext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edittext;

    private ImageView imageview;

    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Button button = findViewById(R.id.MMM_sss);
        edittext = (EditText) findViewById(R.id.YYY_Y);
        imageview = (ImageView) findViewById(R.id.Picture);
        progressbar = findViewById(R.id.progess_bar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressdialog = new ProgressDialog(MainActivity.this);
                progressdialog.setTitle("This is ProgressDialog");
                progressdialog.setMessage("Loading...");
                progressdialog.setCancelable(true);
                progressdialog.show();
                /*AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("This is Dialog");
                dialog.setMessage("Something important");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();*/
//                int progress = progressbar.getProgress();
//                progress = progress + 10;
//                progressbar.setProgress(progress);
//                if(progressbar.getVisibility() == View.GONE)
//                {
//                    progressbar.setVisibility(View.VISIBLE);
//                }
//                else {
//                    progressbar.setVisibility(View.GONE);
//                }
//                imageview.setImageResource(R.drawable.new_3);
//                String inputText = edittext.getText().toString();
//                Toast.makeText(MainActivity.this,inputText,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
