package com.example.testingscrollview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class CareGiverPage extends AppCompatActivity {
    private DatabaseReference root;

    ArrayList<String> filtered = new ArrayList<>();
    String[][] profileArr = {{"Derick Galloway", "Rutland", "He"},{"Elvira Serrano", "Black Mountain", "She"}, {"Johnnie Irwin", "Glenmore", "She"},{"Shana Mejia","Upper Mission",""},{"Marianne Harper","Dilworth","He"},{"Leo Chang","Kettle Valley","He"},{"Valerie Price","Lower Mission","She"},{"Hector Mendoza","Crawford","He"},{"Brenda Nguyen","Mission","He"},{"Isaac Jensen","Glenrosa","She"},{"Stella Carter","Orchard Park","She"},{"Roland Hayes","South Pandosy","She"}};
    Spinner languageSpinner;
    Button applyButton, closeButton;
    RadioGroup genderButton, locationButton, timeButton, serviceButton;
    String selectedGender, selectedLocation, selectedTime, selectedService, selectedLanguage;

    EditText startTime, endTime, wage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_giver_page);

        root = FirebaseDatabase.getInstance().getReference();


        languageSpinner = findViewById(R.id.language);
        String[] languageArr = new String[]{"Arabic", "Chinese", "English", "French", "German", "Japanese", "Korean", "Spanish"};
        ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, languageArr);
        languageSpinner.setAdapter(languageAdapter);

        genderButton = findViewById(R.id.genderRadio);
        genderButton.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            selectedGender = radioButton.getText().toString();
        });

        locationButton = findViewById(R.id.locationGroup);
        locationButton.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            selectedLocation = radioButton.getText().toString();
        });

        timeButton = findViewById(R.id.timeGroup);
        locationButton.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            selectedTime = radioButton.getText().toString();
        });

        serviceButton = findViewById(R.id.serviceGroup);
        serviceButton.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            selectedService = radioButton.getText().toString();
        });

        startTime = findViewById(R.id.startTimeText);
        endTime = findViewById(R.id.endTimeText);
        wage = findViewById(R.id.wageEditText);

        closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CareGiverPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        applyButton = findViewById(R.id.applyButton);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CareGiverPage.this, giverResultPage.class);

                filter(selectedLocation, selectedGender);

                for (int i = 0; i < filtered.size(); i++) {
                    System.out.println(filtered.get(i));
                }
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)filtered);
                intent.putExtra("BUNDLE",args);

                intent.putExtra("gender", selectedGender);
                intent.putExtra("location", selectedLocation);
                intent.putExtra("time", selectedTime);
                intent.putExtra("startTime", startTime.getText().toString());
                intent.putExtra("endTime", endTime.getText().toString());
                intent.putExtra("wage", wage.getText().toString());
                intent.putExtra("service", selectedService);

                String language = (String) languageSpinner.getSelectedItem();
                intent.putExtra("language", language);
                startActivity(intent);



            }
        });
    }
    public void filter(String location, String prounoun){
        for (int i = 0; i < profileArr.length; i++){
            if (profileArr[i][1].equals(location) && profileArr[i][2].equalsIgnoreCase(prounoun)){
                filtered.add(profileArr[i][0]);
            }
        }
        //return filtered;

    }
}