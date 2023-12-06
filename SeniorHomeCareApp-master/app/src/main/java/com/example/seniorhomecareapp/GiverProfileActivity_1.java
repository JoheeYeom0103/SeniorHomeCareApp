package com.example.seniorhomecareapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageButton;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DatabaseReference;


public class GiverProfileActivity_1 extends AppCompatActivity {

    private DatabaseReference databaseReference;
    String userId = "yourUserId";
    TextView name, pronoun, location, availability, phonenum, email;
    EditText nameEd, pronounEd, locationEd, availabilityEd, phonenumEd, emailEd, bioEd, degreeEd, experienceEd, certificationEd;
    ImageButton bannerEditOn, bannerEditOff;
    String nameInput, pronounInput, locationInput, availabilityInput, phonenumInput, emailInput, bioInput, degreeInput, experienceInput, certificationInput;
    List<String> filtered = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//
//        databaseReference = database.getReference("MainProfile").child(userId);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giverprofile_1);

        name = findViewById(R.id.name_giver);
        pronoun = findViewById(R.id.pronoun_giver);
        location = findViewById(R.id.location_giver);
        availability = findViewById(R.id.availability_giver);
        phonenum = findViewById(R.id.phonenum_giver);
        email = findViewById(R.id.email_giver);

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

        bannerEditOff = findViewById(R.id.changebanner_button_giver);
        bannerEditOn = findViewById(R.id.savebanner_button_giver);

        // Initially, set the TextView visible and EditText invisible
        name.setVisibility(View.VISIBLE);
        pronoun.setVisibility(View.VISIBLE);
        location.setVisibility(View.VISIBLE);
        availability.setVisibility(View.VISIBLE);
        phonenum.setVisibility(View.VISIBLE);
        email.setVisibility(View.VISIBLE);
        bannerEditOff.setVisibility(View.VISIBLE);

        nameEd.setVisibility(View.INVISIBLE);
        pronounEd.setVisibility(View.INVISIBLE);
        locationEd.setVisibility(View.INVISIBLE);
        availabilityEd.setVisibility(View.INVISIBLE);
        phonenumEd.setVisibility(View.INVISIBLE);
        emailEd.setVisibility(View.INVISIBLE);
        bannerEditOn.setVisibility(View.INVISIBLE);


        //added by ken:
        ImageButton listviewButtonGiver = findViewById(R.id.listviewButton_giver);
        listviewButtonGiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event
                openListView_giver();
            }
        });
        //end of ken fragment

        //added by ken:
        ImageButton savinglistButtonGiver = findViewById(R.id.savinglistButton_giver);
        savinglistButtonGiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event
                openSaveList_giver();
            }
        });
//end of ken fragment


        filtered.add("TEST");
        filtered.add("TEST@!");

    }

    public void openListView_giver() {
        //Ken method
        Intent intent = new Intent(this, giverprofile_list.class);
        startActivity(intent);
    }

    public void openProfile_giver() {
    }

    public void openSaveList_giver() {
        Intent intent = new Intent(this, LikedGiverActivity.class);

        // Pass the filtered list to LikedGiverActivity
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST", (Serializable) filtered);
        intent.putExtra("BUNDLE", args);

        startActivity(intent);
    }

    public void editBanner_giver(View view) {

        name.setVisibility(View.INVISIBLE);
        pronoun.setVisibility(View.INVISIBLE);
        location.setVisibility(View.INVISIBLE);
        availability.setVisibility(View.INVISIBLE);
        phonenum.setVisibility(View.INVISIBLE);
        email.setVisibility(View.INVISIBLE);
        bannerEditOff.setVisibility(View.INVISIBLE);

        nameEd.setVisibility(View.VISIBLE);
        pronounEd.setVisibility(View.VISIBLE);
        locationEd.setVisibility(View.VISIBLE);
        availabilityEd.setVisibility(View.VISIBLE);
        phonenumEd.setVisibility(View.VISIBLE);
        emailEd.setVisibility(View.VISIBLE);
        bannerEditOn.setVisibility(View.VISIBLE);

    }

    public void saveBannerChange(View view) {

        nameInput = nameEd.getText().toString();
        pronounInput = pronounEd.getText().toString();
        locationInput = locationEd.getText().toString();
        availabilityInput = availabilityEd.getText().toString();
        phonenumInput = phonenumEd.getText().toString();
        emailInput = emailEd.getText().toString();

        if (nameInput.isEmpty() || pronounInput.isEmpty() || availabilityInput.isEmpty() || phonenumInput.isEmpty() || emailInput.isEmpty()) {
            // Display a toast message
            Toast.makeText(GiverProfileActivity_1.this, "Please complete the profile", Toast.LENGTH_SHORT).show();
        } else {

            /* Later add more validity check */
            name.setVisibility(View.VISIBLE);
            pronoun.setVisibility(View.VISIBLE);
            location.setVisibility(View.VISIBLE);
            availability.setVisibility(View.VISIBLE);
            phonenum.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            bannerEditOff.setVisibility(View.VISIBLE);

            nameEd.setVisibility(View.INVISIBLE);
            pronounEd.setVisibility(View.INVISIBLE);
            locationEd.setVisibility(View.INVISIBLE);
            availabilityEd.setVisibility(View.INVISIBLE);
            phonenumEd.setVisibility(View.INVISIBLE);
            emailEd.setVisibility(View.INVISIBLE);
            bannerEditOn.setVisibility(View.INVISIBLE);

            name.setText(nameInput);
            pronoun.setText(pronounInput);
            location.setText(locationInput);
            availability.setText(availabilityInput);
            phonenum.setText(phonenumInput);
            email.setText(emailInput);
        }
    }

    public void next1_giver(View view) {
        // Retrieve the values from the EditText views
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
//
//        // Check if any of the input strings is empty
//        boolean allInputsNotEmpty = true;
//        for (String input : inputFromProfile1) {
//            if (input==null || input.isEmpty()) {
//                allInputsNotEmpty = false;
//                break; // No need to continue checking once one empty input is found
//            }
//        }
//
//        if (allInputsNotEmpty) {

             //Write to database under main profile
//            databaseReference.child("name").setValue(nameInput);
//            databaseReference.child("pronouns").setValue(pronounInput);
//            databaseReference.child("location").setValue(locationInput);
//            databaseReference.child("availability").setValue(availabilityInput);
//            databaseReference.child("phone number").setValue(phonenumInput);
//            databaseReference.child("email").setValue(emailInput);
//            databaseReference.child("bio").setValue(bioInput);
//            databaseReference.child("degree").setValue(degreeInput);
//            databaseReference.child("experience").setValue(experienceInput);
//            databaseReference.child("certification").setValue(certificationInput);

            // Create an Intent
            Intent intent = new Intent(GiverProfileActivity_1.this, GiverProfileActivity_2.class);

            // Create a Bundle
            Bundle bundle = new Bundle();

            // Put the ArrayList<String> into the Bundle
            bundle.putStringArrayList("profile", inputFromProfile1);

            // Attach the Bundle to the Intent
            intent.putExtras(bundle);

            // Start the next activity
            startActivity(intent);
//        } else {
//            // Display a message to the user indicating that all inputs must be filled
//            Toast.makeText(GiverProfileActivity_1.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
//        }
    }
}
