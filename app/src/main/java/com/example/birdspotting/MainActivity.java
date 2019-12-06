package com.example.birdspotting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mainRecyclerView;
    List<Bird> birdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        birdList = new ArrayList<>();
        getJSONfromFile();

        // Recycler View
        mainRecyclerView = findViewById(R.id.main_recycler_view);

        if(MainActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mainRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        else{
            mainRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
       // mainRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Set Adapter
        BirdAdapter adapter = new BirdAdapter(this, birdList);
        mainRecyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);

    }

    public void getJSONfromFile() {
        String jsonString;
        InputStream inputStream;
        int size;
        byte[] buff;
        try {
            inputStream = getAssets().open("birds.json");
            size = inputStream.available();
            buff = new byte[size];
            inputStream.read(buff);
            inputStream.close();

            jsonString = new String(buff, "UTF-8");
            JSONArray jsonArray = new JSONArray(jsonString);

            for(int i = 0; i < jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                Bird bird = new Bird();
                bird.setScientificName(obj.getString("scientific_name"));
                bird.setCommonName(obj.getString("common_name"));
                bird.setNumberImage(obj.getString("image"));
                bird.setDescription(obj.getString("description"));
                birdList.add(bird);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
