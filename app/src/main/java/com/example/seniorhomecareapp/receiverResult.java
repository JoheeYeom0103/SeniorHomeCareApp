package com.example.testingscrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class receiverResult extends AppCompatActivity {

    DatabaseReference databaseReference;

    TextView genderText, locationText, availabilityText, startTimeText, endTimeText, wageMinText, wageMaxText, expTe, serviceText, languageText, vCheckedText,starCheckedText,ratingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        String userId = "Giver3Id";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_result_page);

        genderText = findViewById(R.id.genderText);
        locationText = findViewById(R.id.locationText);
        availabilityText = findViewById(R.id.availabilityText);
        startTimeText = findViewById(R.id.startTimeText);
        endTimeText = findViewById(R.id.endTimeText);
        wageMinText = findViewById(R.id.wageMinText);
        wageMaxText = findViewById(R.id.wageMaxText);
        expTe = findViewById(R.id.expText);
        serviceText = findViewById(R.id.serviceText);
        languageText = findViewById(R.id.languageText);
        ratingText = findViewById(R.id.ratingText);
        vCheckedText = findViewById(R.id.vCheckedText);
        starCheckedText = findViewById(R.id.starCheckedText);

        Intent intent = getIntent();

        String gender = intent.getStringExtra("gender");
        String location = intent.getStringExtra("location");
        String availability = intent.getStringExtra("availability");
        String startTime = intent.getStringExtra("startTime");
        String endTime = intent.getStringExtra("endTime");

        String wageMin = intent.getStringExtra("wageMin");
        String wageMax = intent.getStringExtra("wageMax");
        String exp = intent.getStringExtra("experience");
        String service = intent.getStringExtra("service");
        String language = intent.getStringExtra("language");
        String rating = intent.getStringExtra("rating");
        String vCheck = intent.getStringExtra("vCheck");
        String sCheck = intent.getStringExtra("sCheck");

        genderText.setText("Gender: " + gender);
        locationText.setText("Location: " + location);
        availabilityText.setText("Time of need: " + availability);
        startTimeText.setText("Start Time: " + startTime);
        endTimeText.setText("End Time: " + endTime);
        wageMinText.setText("Min Wage: " + wageMin);
        wageMaxText.setText("Max Wage: " + wageMax);
        expTe.setText("Experience: " + exp);
        serviceText.setText("Type of Service: " + service);
        languageText.setText("Language: " + language);
        ratingText.setText("Rating: " + rating);
        vCheckedText.setText("v Checked?: " + vCheck);
        starCheckedText.setText("star Checked?: " + sCheck);


        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Object> filteredProfile = (ArrayList<Object>) args.getSerializable("ARRAYLIST");
        for (int i = 0; i < filteredProfile.size(); i++) {
            System.out.println(filteredProfile.get(i));
        }

    }
}