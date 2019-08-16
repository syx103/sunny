package com.example.listviewtext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;
    public FruitAdapter(Context context, int textViewResourceId,
                        List<Fruit> object) {
        super(context,textViewResourceId,object);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewholder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewholder = new ViewHolder();
            viewholder.fruitImage = view.findViewById(R.id.picture);
            viewholder.fruitName = view.findViewById(R.id.name);
            view.setTag(viewholder);
        }else {
            view = convertView;
            viewholder = (ViewHolder)view.getTag();
        }
        /*ImageView fruitImage = (ImageView)view.findViewById(R.id.picture);
        TextView fruitName = (TextView)view.findViewById(R.id.name);*/
        viewholder.fruitImage.setImageResource(fruit.getImageId());
        viewholder.fruitName.setText(fruit.getName());
        return view;
    }
    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
    }
}
