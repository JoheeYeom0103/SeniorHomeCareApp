package com.example.seniorhomecareapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Button;
import android.widget.Toast;

public class review extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText nametyping;
    private EditText reviewEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar2);
        nametyping = findViewById(R.id.naming);
        reviewEditText = findViewById(R.id.review);
        Button submit = (Button) findViewById(R.id.submitbutton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nametyping.getText().toString();
                String revieww = reviewEditText.getText().toString();
                float rating = ratingBar.getRating();
                Toast.makeText(getApplicationContext(), "Your review has been submitted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(review.this, reviewIndicator.class);
                intent.putExtra("Name", name);
                intent.putExtra("rating", rating);
                intent.putExtra("review", revieww);
                startActivity(intent);

            }

        });

    }
}