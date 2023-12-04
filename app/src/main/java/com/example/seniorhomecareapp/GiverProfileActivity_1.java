package com.example.seniorhomecareapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageButton;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GiverProfileActivity_1 extends AppCompatActivity {

    private DatabaseReference databaseReference;
    String userId = "Giver1Id";
    TextView name, pronoun, location, availability, phonenum, email;
    EditText nameEd, pronounEd, locationEd, availabilityEd, phonenumEd, emailEd, bioEd, degreeEd, experienceEd, certificationEd;
    ImageButton bannerEditOn, bannerEditOff;
    String nameInput, pronounInput, locationInput, availabilityInput, phonenumInput, emailInput, bioInput, degreeInput, experienceInput, certificationInput;

    Boolean ifRevisit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giverprofile_1);

        ifRevisit = (getIntent().getStringExtra("intent from") == "previous");

        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference(userId).child("MainProfile");

        nameEd = findViewById(R.id.name_edit_giver);
        pronounEd = findViewById(R.id.pronoun_edit_giver);
        locationEd = findViewById(R.id.location_edit_giver);
        availabilityEd = findViewById(R.id.availability_edit_giver);
        phonenumEd = findViewById(R.id.phonenum_edit_giver);
        emailEd = findViewById(R.id.email_edit_giver);
        bioEd = findViewById(R.id.editBio_giver);
        degreeEd = findViewById(R.id.editDegree_giver);
        experienceEd = findViewById(R.id.editExpereince_giver);
        certificationEd = findViewById(R.id.editCertification_giver);

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
                        bioEd.setText(String.valueOf(dataSnapshot.child("bio").getValue()));
                        degreeEd.setText(String.valueOf(dataSnapshot.child("degree").getValue()));
                        experienceEd.setText(String.valueOf(dataSnapshot.child("experience").getValue()));
                        certificationEd.setText(String.valueOf(dataSnapshot.child("certification").getValue()));
                    }
                } else {
                    // Print out error message in Logcat
                    Log.e("Firebase", "Error getting data from Firebase", task.getException());
                }
            });
        }
    }

    public void openListView_giver(View view) {
        /* TODO: ADD YOUR CODE HERE - Ken & Kevin */
    }

    public void openProfile_giver(View view) {

    }

    public void openSaveList_giver(View view) {
        /* TODO: ADD YOUR CODE HERE - Junho */
    }

    public void next1_giver(View view) {
        // Retrieve the values from the EditText views
        nameInput = nameEd.getText().toString();
        pronounInput = pronounEd.getText().toString();
        locationInput = locationEd.getText().toString();
        availabilityInput = availabilityEd.getText().toString();
        phonenumInput = phonenumEd.getText().toString();
        emailInput = emailEd.getText().toString();
        bioInput = bioEd.getText().toString();
        degreeInput = degreeEd.getText().toString();
        experienceInput = experienceEd.getText().toString();
        certificationInput = certificationEd.getText().toString();

        // Create an ArrayList<String> with the input values
        ArrayList<String> inputFromProfile1 = new ArrayList<>();
        inputFromProfile1.add(nameInput);
        inputFromProfile1.add(pronounInput);
        inputFromProfile1.add(locationInput);
        inputFromProfile1.add(availabilityInput);
        inputFromProfile1.add(phonenumInput);
        inputFromProfile1.add(emailInput);
        inputFromProfile1.add(bioInput);
        inputFromProfile1.add(degreeInput);
        inputFromProfile1.add(experienceInput);
        inputFromProfile1.add(certificationInput);

        // Check if any of the input strings is empty
        boolean allInputsNotEmpty = true;
        for (String input : inputFromProfile1) {
            if (input==null || input.isEmpty()) {
                allInputsNotEmpty = false;
                // No need to continue checking once one empty input is found
                break;
            }
        }

        if (allInputsNotEmpty) {

            //Write to database under main profile
            databaseReference.child("name").setValue(nameInput);
            databaseReference.child("pronouns").setValue(pronounInput);
            databaseReference.child("location").setValue(locationInput);
            databaseReference.child("availability").setValue(availabilityInput);
            databaseReference.child("phone number").setValue(phonenumInput);
            databaseReference.child("email").setValue(emailInput);
            databaseReference.child("bio").setValue(bioInput);
            databaseReference.child("degree").setValue(degreeInput);
            databaseReference.child("experience").setValue(experienceInput);
            databaseReference.child("certification").setValue(certificationInput);

            Toast.makeText(GiverProfileActivity_1.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
            // Create an Intent
            Intent nextIntent = new Intent(GiverProfileActivity_1.this, ProfileActivity_2.class);
            // To Check if the page has been visited before
            nextIntent.putExtra("intent from", "next");
            // To maintain data under the same user id
            nextIntent.putExtra("userId", userId);  // Pass the userId to the next activity
            // Start the next activity
            startActivity(nextIntent);
            Log.d("3411", "working");

        } else {
            // Display a message to the user indicating that all inputs must be filled
            Toast.makeText(GiverProfileActivity_1.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
        }
    }
}
