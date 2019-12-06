package com.example.birdspotting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookActivity extends AppCompatActivity {

    // Views
    private ImageView birdImage;
    private TextView birdCommonName;
    private TextView birdSciName;
    private TextView birdDesc;
    private TextView birdCount;
    private Button countButton;
    private int count = 0;
    Context mContext;

    // SharedPreferences Keys
    public String SHARED_PREFS;
    public String COUNT = "count";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // Views
        birdCommonName = findViewById(R.id.com_name);
        birdSciName = findViewById(R.id.sci_name);
        birdDesc = findViewById(R.id.description);
        birdImage = findViewById(R.id.bird_image);
        birdCount = findViewById(R.id.bird_count);
        countButton = findViewById(R.id.count_button);
        mContext = getApplicationContext();

        // Receive data
        Intent intent = getIntent();
        String commonName = intent.getExtras().getString("CommonName");
        String sciName = intent.getExtras().getString("SciName");
        String desc = intent.getExtras().getString("Description");
        String img = intent.getExtras().getString("Image");

        // The shared prefs depends on the bird name
        SHARED_PREFS = commonName;

        // Set values
        birdCommonName.setText(commonName);
        birdSciName.setText(sciName);
        birdDesc.setText(desc);
        birdImage.setImageResource(getImageId(mContext, img));

        // OnClickListener for the Count Button
        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                birdCount.setText(count + "");
            }
        });

        // Load the data from saved preferences
        loadData();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(COUNT, count);
        editor.apply();
       // Toast.makeText(this, "Data saved", Toast.LENGTH_LONG).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        count = sharedPreferences.getInt(COUNT, MODE_PRIVATE);
        birdCount.setText(String.valueOf(count));
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }
}
