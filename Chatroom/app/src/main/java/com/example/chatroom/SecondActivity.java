package com.example.chatroom;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private String  name;
    private String ip;
    private String port;
    private Button send;
    private Button back;
    private Button edit;
    private Socket socketsend;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    private StringBuilder sb = new StringBuilder();
    private InputStream receiveInput;
    private OutputStream sendOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        ip = intent.getStringExtra("ip");
        port = intent.getStringExtra("port");
        msgRecyclerView = findViewById(R.id.msg_recycle_view);
        back =  findViewById(R.id.back);
        edit =  findViewById(R.id.edit);
        send =  findViewById(R.id.send);
        inputText = findViewById(R.id.input_text);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath().build());
        try {
           socketsend = new Socket(ip,Integer.parseInt(port));
           sendOutput = socketsend.getOutputStream();
           receiveInput = socketsend.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (socketsend == null) {
            Toast.makeText(this, "连接失败", Toast.LENGTH_SHORT).show();
            //finish();
        } else {
            Toast.makeText(this, "已登录", Toast.LENGTH_SHORT).show();
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] bb = new byte[1024 * 5];
                    int lenth ;
                    String receiverMsg;
                    while (true) {
                        while ((lenth = receiveInput.read(bb)) != -1 ) {
                            receiverMsg = new String(bb,0, lenth);
                            final Msg msg = new Msg(receiverMsg, Msg.TYPE_PECEIVED);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    msgList.add(msg);
                                    adapter.notifyItemInserted(msgList.size() - 1);
                                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
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
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SecondActivity.this);
                dialog.setTitle("确认退出");
                dialog.setMessage("是否要退出聊天?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                        startActivity(intent);*/
                        finish();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
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
                    String content = inputText.getText().toString();
                    if (!"".equals(content)) {
                        sb.append(content).append("\n\n").append("用户:").append(name).append("\n");
                        content = sb.toString();
                        try {
                            sendOutput.write(content.getBytes());
                            sb.delete(0,sb.length());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Msg msg = new Msg(content, Msg.TYPE_SENT);
                        msgList.add(msg);
                        adapter.notifyItemInserted(msgList.size() - 1);
                        msgRecyclerView.scrollToPosition(msgList.size() - 1);
                        inputText.setText("");
                    }
            }
        });
    }
}