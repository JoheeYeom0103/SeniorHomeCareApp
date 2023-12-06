package com.example.seniorhomecareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LikedGiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_giver);

        // Get the ArrayList from the Intent
        Bundle bundle = getIntent().getBundleExtra("BUNDLE");
        if (bundle != null) {
            ArrayList<String> likedUsers = (ArrayList<String>) bundle.getSerializable("ARRAYLIST");

            // Display the liked users in a ListView
            ListView likedUsersListView = findViewById(R.id.likedUsersListView);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, likedUsers);
            likedUsersListView.setAdapter(adapter);
        }
        ImageButton backButton = findViewById(R.id.backButton);

        // Set a click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle back button click, for example, navigate back to the main page
                Intent intent = new Intent(LikedGiverActivity.this, GiverProfileActivity_1.class);
                startActivity(intent);
            }
        });
    }
}
