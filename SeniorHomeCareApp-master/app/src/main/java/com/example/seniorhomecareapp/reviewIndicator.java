package com.example.seniorhomecareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.Toast;

public class reviewIndicator extends AppCompatActivity {
    private TextView nameView;
    private TextView reviewView;
    private RatingBar ratingBar;

    Button goBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_indicator);
        nameView=findViewById(R.id.namingView);
        reviewView=findViewById(R.id.reviewresult);
        ratingBar=findViewById(R.id.ratingBar);
        goBack=findViewById(R.id.backButton);
        Bundle extras = getIntent().getExtras();

        String name=extras.getString("Name");
        float rating=extras.getFloat("rating");
        String review=extras.getString("review");

        if(extras!=null) {
            nameView.setText(name);
            reviewView.setText(review);
            ratingBar.setRating(rating);
        }
        goBack.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {

                Intent intent=new Intent(reviewIndicator.this, giverprofile_list.class);
                startActivity(intent);

            }
        });
    }}



