package com.example.seniorhomecareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class CareReceiverPage extends AppCompatActivity {
    ArrayList<String> filtered = new ArrayList<>();
    String[][] profileArr = {{"Derick Galloway", "Rutland", "He"},{"Elvira Serrano", "Black Mountain", "She"}, {"Johnnie Irwin", "Glenmore", "She"},{"Shana Mejia","Upper Mission",""},{"Marianne Harper","Dilworth","He"},{"Leo Chang","Kettle Valley","He"},{"Valerie Price","Lower Mission","She"},{"Hector Mendoza","Crawford","He"},{"Brenda Nguyen","Mission","He"},{"Isaac Jensen","Glenrosa","She"},{"Stella Carter","Orchard Park","She"},{"Roland Hayes","South Pandosy","She"}};
    Spinner experienceSpinner,languageSpinner,ratingSpinner;
    Button closeButton, applyButton;
    RadioGroup genderButton, locationButton, availabilityButton, serviceButton;
    String selectedGender, selectedLocation, selectedAvailability, selectedService;
    EditText startTime,endTime,wageMin,wageMax;
    CheckBox vCheck, starCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_receiver_page);

        experienceSpinner = findViewById(R.id.experienceSpinner);
        String[] experienceArr = new String[]{"less than 1 year", "1 ~ 3 years", " 3 ~ 5 years", "5 ~ 10 years", "more than 10 years"};
        ArrayAdapter<String> expAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, experienceArr);
        experienceSpinner.setAdapter(expAdapter);

        languageSpinner = findViewById(R.id.language);
        String[] languageArr = new String[]{"Arabic", "Chinese", "English", "French", "German", "Japanese", "Korean", "Spanish"};
        ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, languageArr);
        languageSpinner.setAdapter(languageAdapter);

        ratingSpinner = findViewById(R.id.ratingSpinner);
        String[] ratingArr = new String[]{"*", "* *", "* * *", "* * * *", "* * * * *"};
        ArrayAdapter<String> ratingAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, ratingArr);
        ratingSpinner.setAdapter(ratingAdapter);

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

        availabilityButton = findViewById(R.id.availabilityGroup);
        availabilityButton.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            selectedAvailability = radioButton.getText().toString();
        });

        startTime = findViewById(R.id.startTimeText);
        endTime = findViewById(R.id.endTimeText);
        wageMin = findViewById(R.id.minWageText);
        wageMax = findViewById(R.id.maxWageText);

        serviceButton = findViewById(R.id.serviceGroup);
        serviceButton.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            selectedService = radioButton.getText().toString();
        });

        vCheck = findViewById(R.id.checkBadge);
        starCheck = findViewById(R.id.starBadge);

        closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CareReceiverPage.this, giverprofile_list.class);
                startActivity(intent);
            }
        });
        applyButton = findViewById(R.id.applyButton);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CareReceiverPage.this, receiverResult.class);

                filter(selectedLocation, selectedGender);

                for (int i = 0; i < filtered.size(); i++) {
                    System.out.println(filtered.get(i));
                }

                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)filtered);
                intent.putExtra("BUNDLE",args);
                //startActivity(intent);

                intent.putExtra("gender", selectedGender);
                intent.putExtra("location", selectedLocation);
                intent.putExtra("availability", selectedAvailability);

                intent.putExtra("startTime", startTime.getText().toString());
                intent.putExtra("endTime", endTime.getText().toString());
                intent.putExtra("wageMin", wageMin.getText().toString());
                intent.putExtra("wageMax", wageMax.getText().toString());

                String exp = (String) experienceSpinner.getSelectedItem();
                intent.putExtra("experience", exp);
                intent.putExtra("service", selectedService);

                String language = (String) languageSpinner.getSelectedItem();
                intent.putExtra("language", language);

                String rating = (String) ratingSpinner.getSelectedItem();
                intent.putExtra("rating", rating);

                String vCheckStr = "False";
                String sCheckStr = "False";
                if (vCheck.isChecked()){
                    vCheckStr = "True";
                }
                if (starCheck.isChecked()){
                    sCheckStr = "True";
                }

                intent.putExtra("vCheck", vCheckStr);
                intent.putExtra("sCheck", sCheckStr);

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