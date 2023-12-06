
package com.example.seniorhomecareapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ProfileActivity_2 extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private CalendarView calendarView;

    private Spinner startHourSpinner, startAmPmSpinner, endHourSpinner, endAmPmSpinner;

    private ArrayAdapter<String> arrayAdapter;

    ImageButton updateTimeButton;

    String userId;

    protected void onCreate(Bundle savedInstanceState) {

        Log.d("3411", "working activity2");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giverprofile_2);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        Log.d("3411", "working activity2-1");

        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(userId).child("Availability");

        calendarView = findViewById(R.id.calendarView);
        startHourSpinner = findViewById(R.id.startHourSpinner);
        startAmPmSpinner = findViewById(R.id.startAmPmSpinner);
        endHourSpinner = findViewById(R.id.endHourSpinner);
        endAmPmSpinner = findViewById(R.id.endAmPmSpinner);
        updateTimeButton = findViewById(R.id.savetimebutton);

        // Set up hour spinners
        ArrayAdapter<String> hourAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.hours));
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startHourSpinner.setAdapter(hourAdapter);
        endHourSpinner.setAdapter(hourAdapter);

        // Set up AM/PM spinners
        ArrayAdapter<String> amPmAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.am_pm));
        amPmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startAmPmSpinner.setAdapter(amPmAdapter);
        endAmPmSpinner.setAdapter(amPmAdapter);

        // Initialize arrayAdapter
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.hours));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /* Need to figure out how to make change to other dates as well */
        // Set up CalendarView listener
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String dateKey = year + "_" + month + "_" + dayOfMonth;

                DatabaseReference dateRef = databaseReference.child(dateKey);
                dateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Data for the selected date exists in Firebase
                            Log.d("3411", "The datekey exists on the database: " + year + "" + month + "" + dayOfMonth);
                            String savedStartHour = dataSnapshot.child("startHour").getValue(String.class);
                            String savedStartAmPm = dataSnapshot.child("startAmPm").getValue(String.class);
                            String savedEndHour = dataSnapshot.child("endHour").getValue(String.class);
                            String savedEndAmPm = dataSnapshot.child("endAmPm").getValue(String.class);

                            // Set the saved time in spinners
                            startHourSpinner.setSelection(arrayAdapter.getPosition(savedStartHour));
                            startAmPmSpinner.setSelection(arrayAdapter.getPosition(savedStartAmPm));
                            endHourSpinner.setSelection(arrayAdapter.getPosition(savedEndHour));
                            endAmPmSpinner.setSelection(arrayAdapter.getPosition(savedEndAmPm));
                        } else {
                            Log.d("3411", "The datekey doesn't exists on the database");
                            // No data for the selected date in Firebase, set initial values
                            setInitialTimeSelection();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle database error
                    }
                });
            }
        });
    }

    private void saveTimeForDate() {
        // Get the current selected date from the CalendarView
        long selectedDateMillis = calendarView.getDate();
        // Convert the milliseconds to year, month, and day
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(selectedDateMillis);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Save data to Firebase
        String dateKey = year + "_" + month + "_" + dayOfMonth;
        DatabaseReference dateRef = databaseReference.child(dateKey);

        dateRef.child("startHour").setValue(startHourSpinner.getSelectedItem().toString());
        dateRef.child("startAmPm").setValue(startAmPmSpinner.getSelectedItem().toString());
        dateRef.child("endHour").setValue(endHourSpinner.getSelectedItem().toString());
        dateRef.child("endAmPm").setValue(endAmPmSpinner.getSelectedItem().toString());
    }

    private void setInitialTimeSelection() {
        // Set initial time selection (you may retrieve this from storage/database)
        startHourSpinner.setSelection(0); // Set initial start hour
        startAmPmSpinner.setSelection(0); // Set initial start AM/PM
        endHourSpinner.setSelection(0);   // Set initial end hour
        endAmPmSpinner.setSelection(0);   // Set initial end AM/PM
    }

    public void saveTimeForDate(View view) {
        saveTimeForDate();
    }

    public void next2_giver(View view) {
        Toast.makeText(ProfileActivity_2.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();

        Intent nextIntent = new Intent(ProfileActivity_2.this, ProfileActivity_3.class);
        nextIntent.putExtra("intent from", "next");
        nextIntent.putExtra("userId", userId);  // Pass the userId to the next activity
        // Start the next activity
        startActivity(nextIntent);
    }

    public void prev2_giver (View view){
        Intent backIntent = new Intent(ProfileActivity_2.this, GiverProfileActivity_1.class);
        backIntent.putExtra("intent from", "previous");
        // Start the next activity
        startActivity(backIntent);
    }

    public void openListview (View view){
        Intent intent = new Intent(this,receiver_listView_Activity.class);
        startActivity(intent);
    }
}

