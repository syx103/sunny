package com.example.chatroom;

public class Msg {
    public static final int TYPE_PECEIVED = 0;  //收到
    public static final int TYPE_SENT = 1;      //发出
    private String content;
    private int type;
    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public int getType() {
        return type;
    }
}
