package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
       /* LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);*/
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }
    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            fruitList.add(new Fruit(getRandomLengthName("Apple"),R.drawable.bear));
            fruitList.add(new Fruit(getRandomLengthName("Banana"),R.drawable.bear));
            fruitList.add(new Fruit(getRandomLengthName("Bear"),R.drawable.bear));
            fruitList.add(new Fruit(getRandomLengthName("Orange"),R.drawable.bear));
            fruitList.add(new Fruit(getRandomLengthName("Watermelon"),R.drawable.bear));
            fruitList.add(new Fruit(getRandomLengthName("Strawberry"),R.drawable.bear));
            fruitList.add(new Fruit(getRandomLengthName("Grape"),R.drawable.bear));
            fruitList.add(new Fruit(getRandomLengthName("Pineapple"),R.drawable.bear));
            fruitList.add(new Fruit(getRandomLengthName("Cherry"),R.drawable.bear));
            fruitList.add(new Fruit(getRandomLengthName("Mango"),R.drawable.bear));
        }
    }
    private String  getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i < length;i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}
