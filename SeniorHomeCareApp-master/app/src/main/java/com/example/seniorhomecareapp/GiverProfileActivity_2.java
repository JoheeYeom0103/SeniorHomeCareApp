package com.example.seniorhomecareapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class GiverProfileActivity_2 extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private CalendarView calendarView;

    String userId = "yourUserId";
    private Spinner startHourSpinner, startAmPmSpinner, endHourSpinner, endAmPmSpinner;

    private ArrayAdapter<String> arrayAdapter;

    protected void onCreate(Bundle savedInstanceState) {

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("Availability").child(userId);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giverprofile_2);

        calendarView = findViewById(R.id.calendarView);
        startHourSpinner = findViewById(R.id.startHourSpinner);
        startAmPmSpinner = findViewById(R.id.startAmPmSpinner);
        endHourSpinner = findViewById(R.id.endHourSpinner);
        endAmPmSpinner = findViewById(R.id.endAmPmSpinner);

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

        // Set up OnItemSelectedListener for startHourSpinner
        startHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                saveTimeForDate(); // Save the selected value
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here if needed
            }
        });

        // Set up OnItemSelectedListener for startAmPmSpinner
        startAmPmSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                saveTimeForDate(); // Save the selected value
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here if needed
            }
        });

        // Set up OnItemSelectedListener for endHourSpinner
        endHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                saveTimeForDate(); // Save the selected value
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here if needed
            }
        });

        // Set up OnItemSelectedListener for endAmPmSpinner
        endAmPmSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                saveTimeForDate(); // Save the selected value
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here if needed
            }
        });

        // Set up CalendarView listener
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                loadDataForSelectedDate(year, month, dayOfMonth);
            }
        });

        // Load initial data on activity start for the current date
        Calendar currentDate = Calendar.getInstance();
        loadDataForSelectedDate(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
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

    private void loadDataForSelectedDate(int year, int month, int dayOfMonth) {
        String dateKey = year + "_" + month + "_" + dayOfMonth;

        databaseReference.child(userId).child(dateKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Data for the selected date exists in Firebase
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
                    // No data for the selected date in Firebase, set default values or do nothing
                    setInitialTimeSelection();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }

    private void setInitialTimeSelection() {
        // Set initial time selection (you may retrieve this from storage/database)
        startHourSpinner.setSelection(0); // Set initial start hour
        startAmPmSpinner.setSelection(0); // Set initial start AM/PM
        endHourSpinner.setSelection(0);   // Set initial end hour
        endAmPmSpinner.setSelection(0);   // Set initial end AM/PM
    }
}
