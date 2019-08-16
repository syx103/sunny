package com.example.chatroom2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Second extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private String name;
    private String ip;
    private String port;
    private Button send;
    private Button back;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    private Socket socket ;
    private InputStream receive;
    private OutputStream sendOut;
    private String content;
    private StringBuilder builder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgRecyclerView = findViewById(R.id.msg_recycle_view);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        ip = intent.getStringExtra("ip");
        port = intent.getStringExtra("port");

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath().build());

        try{
            socket = new Socket(ip,Integer.parseInt(port));
            sendOut = socket.getOutputStream();
            receive = socket.getInputStream();
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(socket == null) {
            Toast.makeText(this,"连接失败",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        new Thread(new Runnable() {
            public void run() {
                try{
                    byte[] arr = new byte[1024 * 8];
                    int len;
                    String receiveMsg;
                    while(true) {
                        while((len = receive.read(arr)) != -1) {
                            receiveMsg = new String(arr,0,len);
                            final Msg msg = new Msg(receiveMsg,Msg.TYPE_PECEIVED);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    msgList.add(msg);
                                    adapter.notifyItemInserted(msgList.size() - 1);//有新消息时，刷新RecyclerView中的显示
                                    msgRecyclerView.scrollToPosition(msgList.size() - 1);//将RecyclerView定位到最后一行

                                }
                            });

                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


       back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Second.this);
                dialog.setTitle("退出");
                dialog.setMessage("退出登录？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    content = inputText.getText().toString();
                    String date = getCurrentTime();
                    if(!"".equals(content)) {
                        builder.append(content).append("\n\n\n" + name).append("\n" + date);
                        content = builder.toString();
                        sendOut.write(content.getBytes());
                        builder.delete(0,builder.length());
                        Msg msg = new Msg(content,Msg.TYPE_SENT);
                        msgList.add(msg);
                        adapter.notifyItemInserted(msgList.size() - 1);
                        msgRecyclerView.scrollToPosition(msgList.size() - 1);
                        inputText.setText("");
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private static String getCurrentTime() {
        @SuppressLint("SimpleDateFormat")
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        return date;
    }
}
