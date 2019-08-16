package com.example.chatroom;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .msg_item, parent, false);
        return new ViewHolder(view);
    }
    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
        TextView time;

        public ViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.time);
            leftLayout =  view.findViewById(R.id.left_layout);
            rightLayout =  view.findViewById(R.id.right_layout);
            leftMsg =  view.findViewById(R.id.left_msg);
            rightMsg =  view.findViewById(R.id.right_msg);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Msg msg = mMsgList.get(position);
        holder.time.setText(MsgAdapter.getCurrentTime());
        if (msg.getType() == Msg.TYPE_PECEIVED) {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
        } else /*if (msg.getType() == Msg.TYPE_SENT)*/ {
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(msg.getContent());
        }

    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }


    public MsgAdapter(List<Msg> msgList) {
        mMsgList = msgList;
    }

    private static String getCurrentTime() {
        @SuppressLint("SimpleDateFormat")
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        return date;
    }
}
