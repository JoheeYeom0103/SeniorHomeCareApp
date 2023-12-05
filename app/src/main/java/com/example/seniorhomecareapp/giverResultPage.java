package com.example.testingscrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class giverResultPage extends AppCompatActivity {
    private DatabaseReference root;

    TextView genderText, locationText, timeText, startTimeText, endTimeText, wageText, serviceText, languageText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giver_result_page);

        genderText = findViewById(R.id.genderText);
        locationText = findViewById(R.id.locationText);
        timeText = findViewById(R.id.timeNeedText);
        startTimeText = findViewById(R.id.startTimeText);
        endTimeText = findViewById(R.id.endTimeText);
        wageText = findViewById(R.id.wageText);
        serviceText = findViewById(R.id.serviceText);
        languageText = findViewById(R.id.languageText);

        Intent intent = getIntent();
        String gender = intent.getStringExtra("gender");
        String location = intent.getStringExtra("location");
        String timeNeed = intent.getStringExtra("time");
        String startTime = intent.getStringExtra("startTime");
        String endTime = intent.getStringExtra("endTime");
        String wage = intent.getStringExtra("wage");
        String service = intent.getStringExtra("service");
        String language = intent.getStringExtra("language");


        genderText.setText("Gender: " + gender);
        locationText.setText("Location: " + location);
        timeText.setText("Time of need: " + timeNeed);
        startTimeText.setText("Start Time: " + startTime);
        endTimeText.setText("End Time: " + endTime);
        wageText.setText("Wage: " + wage);
        serviceText.setText("Type of Service: " + service);
        languageText.setText("Language: " + language);

        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Object> filteredProfile = (ArrayList<Object>) args.getSerializable("ARRAYLIST");
        for (int i = 0; i < filteredProfile.size(); i++) {
            System.out.println(filteredProfile.get(i));
        }
    }
}