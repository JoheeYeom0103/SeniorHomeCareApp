package com.example.seniorhomecareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class SigninActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    public void openGiverProfile(View view) {
        // Send an intent to GiverProfileActivity
        Intent intent = new Intent(SigninActivity.this, GiverProfileActivity_1.class);
        startActivity(intent);
    }
    public void openReceiverProfile(View view) {
        // Send an intent to ReceiverProfileActivity
        Intent intent = new Intent(SigninActivity.this, ReceiverProfileActivity.class);
        startActivity(intent);
    }


}