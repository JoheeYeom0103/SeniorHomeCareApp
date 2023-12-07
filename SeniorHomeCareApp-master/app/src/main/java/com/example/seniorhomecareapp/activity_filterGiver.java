package com.example.seniorhomecareapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class activity_filterGiver extends AppCompatActivity {
    private EditText editTextLocation;
    private EditText editTextBio;
    public giverProfile_adapter profileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_giver);

        editTextLocation = findViewById(R.id.editTextLocation);
        editTextBio = findViewById(R.id.editTextBio);
        /*
        Button applyFilterButton = findViewById(R.id.applyFilterButton);
        applyFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyFilter();
            }
        });

         */
    }
    /*
    private void applyFilter() {
        // Get the filter criteria
        String locationFilter = editTextLocation.getText().toString();
        String bioFilter = editTextBio.getText().toString();

        // Apply the filter to the adapter
        if (profileAdapter != null) {
            profileAdapter.applyFilter(locationFilter, bioFilter);
        }

        // Close the filter activity
        finish();
    }

     */
}
