package com.example.seniorhomecareapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReceiverProfileActivity_1 extends AppCompatActivity {
    private DatabaseReference databaseReference;
    String userId = "Receiver1Id";
    EditText nameEd, pronounEd, locationEd, availabilityEd, phonenumEd, emailEd, bioEd, medicalInputEd, preferenceInputEd;

    Boolean ifRevisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("3411", "Activity opened");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiverprofile);

        ifRevisit = getIntent().getStringExtra("intent from").equals("previous");

        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference(userId).child("MainProfile");

        nameEd = findViewById(R.id.name_edit_receiver);
        pronounEd = findViewById(R.id.pronoun_edit_receiver);
        locationEd = findViewById(R.id.location_edit_receiver);
        availabilityEd = findViewById(R.id.availability_edit_receiver);
        phonenumEd = findViewById(R.id.phonenum_edit_receiver);
        emailEd = findViewById(R.id.email_edit_receiver);
        medicalInputEd = findViewById(R.id.medicalInput);
        preferenceInputEd = findViewById(R.id.preferenceInput);

        if (ifRevisit) {
            databaseReference.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DataSnapshot dataSnapshot = task.getResult();
                    if (dataSnapshot != null) {
                        // Set the retrieved values to the corresponding EditText fields
                        nameEd.setText(String.valueOf(dataSnapshot.child("name").getValue()));
                        pronounEd.setText(String.valueOf(dataSnapshot.child("pronouns").getValue()));
                        locationEd.setText(String.valueOf(dataSnapshot.child("location").getValue()));
                        availabilityEd.setText(String.valueOf(dataSnapshot.child("availability").getValue()));
                        phonenumEd.setText(String.valueOf(dataSnapshot.child("phone number").getValue()));
                        emailEd.setText(String.valueOf(dataSnapshot.child("email").getValue()));
                        medicalInputEd.setText(String.valueOf(dataSnapshot.child("medicalCondition").getValue()));
                        preferenceInputEd.setText(String.valueOf(dataSnapshot.child("preferences").getValue()));
                    }
                } else {
                    // Handle the error
                    Log.e("Firebase", "Error getting data from Firebase", task.getException());
                }
            });
        }
    }

    public void openListView_receiver(View view) {
        Intent intent = new Intent(this,giverprofile_list.class);
        startActivity(intent);
    }

    public void openProfile_receiver(View view) {
    }

    public void openSaveList_receiver(View view) {
        /* TODO: ADD YOUR CODE HERE - Junho */
    }

    public void next1_receiver(View view) {
        // Retrieve the values from the EditText views
        String nameInput = nameEd.getText().toString();
        String pronounInput = pronounEd.getText().toString();
        String locationInput = locationEd.getText().toString();
        String availabilityInput = availabilityEd.getText().toString();
        String phonenumInput = phonenumEd.getText().toString();
        String emailInput = emailEd.getText().toString();
        String medicalInput = medicalInputEd.getText().toString();
        String preferenceInput = preferenceInputEd.getText().toString();

        // Create an ArrayList<String> with the input values
        ArrayList<String> inputFromProfile = new ArrayList<>();
        inputFromProfile.add(nameInput);
        inputFromProfile.add(pronounInput);
        inputFromProfile.add(locationInput);
        inputFromProfile.add(availabilityInput);
        inputFromProfile.add(phonenumInput);
        inputFromProfile.add(emailInput);
        inputFromProfile.add(medicalInput);
        inputFromProfile.add(preferenceInput);

        // Check if any of the input strings is empty
        boolean allInputsNotEmpty = true;
        for (String input : inputFromProfile) {
            if (input == null || input.isEmpty()) {
                allInputsNotEmpty = false;
                break; // No need to continue checking once one empty input is found
            }
        }

        if (allInputsNotEmpty) {
            // Write to the database under main profile
            databaseReference.child("name").setValue(nameInput);
            databaseReference.child("pronouns").setValue(pronounInput);
            databaseReference.child("location").setValue(locationInput);
            databaseReference.child("availability").setValue(availabilityInput);
            databaseReference.child("phone number").setValue(phonenumInput);
            databaseReference.child("email").setValue(emailInput);
            databaseReference.child("medicalCondition").setValue(medicalInput);
            databaseReference.child("preferences").setValue(preferenceInput);

            Toast.makeText(ReceiverProfileActivity_1.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();

            Intent nextIntent = new Intent(ReceiverProfileActivity_1.this, ProfileActivity_2.class);
            nextIntent.putExtra("intent from", "next");
            nextIntent.putExtra("userId", userId);  // Pass the userId to the next activity
            startActivity(nextIntent);

        } else {
            // Display a message to the user indicating that all inputs must be filled
            Toast.makeText(ReceiverProfileActivity_1.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
        }
    }
}
