package com.example.seniorhomecareapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProfileActivity_3 extends AppCompatActivity {

    private DatabaseReference databaseReference1;
    private DatabaseReference databaseReference2;

    String userId;

    CheckBox physioCh, monitoringCh, medicationCh, mobilityCh, companionCh, dailyCh, otherCh;
    EditText fromWage, toWage;

    TextView otherEd;

    protected void onCreate(Bundle savedInstanceState) {

        Log.d("3411", "working activity3");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giverprofile_3);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference1 = database.getReference(userId).child("ServiceType");
        databaseReference2 = database.getReference(userId).child("Wage");

        physioCh = findViewById(R.id.physioCh);
        monitoringCh = findViewById(R.id.monitoringCh);
        medicationCh = findViewById(R.id.medicationCh);
        mobilityCh = findViewById(R.id.mobilityCh);
        companionCh = findViewById(R.id.companionCh);
        dailyCh = findViewById(R.id.dailyCh);

        otherCh = findViewById(R.id.otherCh);
        otherEd = findViewById(R.id.otherEd);

        fromWage = findViewById(R.id.fromWage);
        toWage = findViewById(R.id.toWage);

    }

    //onClick needs View!!
    public void next3_giver(View view) {

        Log.d("3411", "Function is called");
        // Initialize an array to store the checked services
        ArrayList<String> checkedServices = new ArrayList<>();

        Log.d("3411", "Something wrong with checkbox");

        // Check each checkbox and add the text to the array for the checked ones
        if (physioCh.isChecked()) checkedServices.add(physioCh.getText().toString());
        if (monitoringCh.isChecked()) checkedServices.add(monitoringCh.getText().toString());
        if (medicationCh.isChecked()) checkedServices.add(medicationCh.getText().toString());
        if (mobilityCh.isChecked()) checkedServices.add(mobilityCh.getText().toString());
        if (companionCh.isChecked()) checkedServices.add(companionCh.getText().toString());
        if (dailyCh.isChecked()) checkedServices.add(dailyCh.getText().toString());

        // Add other conditions as needed

        Log.d("3411", "Something wrong with datatype");

        // Get the text from the EditText views
        String fromWageText = fromWage.getText().toString();
        String toWageText = toWage.getText().toString();

        for (int i = 0; i < checkedServices.size(); i++) {
            String serviceTypeKey = "serviceType" + (i + 1);
            String serviceName = checkedServices.get(i);
            databaseReference1.child(serviceTypeKey).setValue(serviceName);
        }

        // Add code to handle other input if needed
        if (otherCh.isChecked()) {
            String otherInput = otherEd.getText().toString();
            databaseReference1.child("serviceTypeOther").setValue(otherInput);
        }

        // Write fromWage and toWage to the database
        databaseReference2.child("fromWage").setValue(fromWageText);
        databaseReference2.child("toWage").setValue(toWageText);

        Toast.makeText(ProfileActivity_3.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();

    }

    public void prev3_giver (View view){
        Intent backIntent = new Intent(ProfileActivity_3.this, ProfileActivity_2.class);
        backIntent.putExtra("intent from", "previous");
        backIntent.putExtra("userId", userId);  // Pass the userId to the next activity
        // Start the next activity
        startActivity(backIntent);
    }

}
