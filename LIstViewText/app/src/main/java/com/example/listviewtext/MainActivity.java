package com.example.listviewtext;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();
//    private String[] data={"Apple","Banana","Orange",
//            "Watermelon","Mango","Pear","Grape","Pineapple",
//            "Cherry","Strawberry","Apple","Banana",
//            "Orange","Watermelon","Mango","Pear","Grape",
//            "Pineapple","Cherry","Strawberry"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruit fruit = fruitList.get(i);
                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);*/
    }

    private void initFruits() {
        for (int i = 0;i < 2;i++) {
            Fruit apple = new Fruit("Apple",R.drawable.apple);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana",R.drawable.banana);
            fruitList.add(banana);
            Fruit bear = new Fruit("Bear",R.drawable.bear);
            fruitList.add(bear);
            Fruit orange = new Fruit("Orange",R.drawable.orange);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon",R.drawable.watermelon);
            fruitList.add(watermelon);
            Fruit strawberry = new Fruit("Strawberry",R.drawable.strawberry);
            fruitList.add(strawberry);
            Fruit grape = new Fruit("Grape",R.drawable.grape);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple",R.drawable.pineapple);
            fruitList.add(pineapple);
            Fruit cherry = new Fruit("Cherry",R.drawable.cherry);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango",R.drawable.mango);
            fruitList.add(mango);
        }
    }
}
