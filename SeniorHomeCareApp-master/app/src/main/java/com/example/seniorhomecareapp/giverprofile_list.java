package com.example.seniorhomecareapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class giverprofile_list extends AppCompatActivity {
    //Ken file!

    private RecyclerView profileRecyclerView;
    private giverProfile_adapter profileAdapter;
    private boolean isHighlighted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giverprofile_list);

        profileRecyclerView = findViewById(R.id.GiverRecyclerView);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Populate with dummy data (replace this with real data retrieval)
        List<Profile> dummyProfiles = createDummyProfiles();
        profileAdapter = new giverProfile_adapter(dummyProfiles);
        profileRecyclerView.setAdapter(profileAdapter);

    }

    private List<Profile> createDummyProfiles() {
        List<Profile> profiles = new ArrayList<>();

        profiles.add(new Profile("Name: John Doe","Location: Glenmore","Time: 7 am to 7 pm", "Registered Nurse",4,true, true));
        profiles.add(new Profile("Name: George Mcneil", "Location: Lower Mission","Time: 7 am to 3 pm","certified Nurse Assistant",5, false, true));
        profiles.add(new Profile("Name: Laura Williams", "Location: Rutland","Time: 12 pm to 5 pm","Nurse Practitioner",3, true, false));
        profiles.add(new Profile("Name: Kira Finley","Location: Kettle Valley","Time: 8 am to 4 pm", "Pediatric Nurse",4, false, false));
        profiles.add(new Profile("Name: Alex Thompson","Location: Dilworth","Time: 9 am to 6 pm", "Emergency Room Nurse",4,true, false));
        profiles.add(new Profile("Name: Emily Rodriguez", "Location: Mission","Time: 10 am to 7 pm","Surgical Nurse",5, true, true));
        profiles.add(new Profile("Name: Michael Turner", "Location: Orchard Park","Time: 8 am to 4 pm","Geriatric Nurse",3, false, true));
        profiles.add(new Profile("Name: Jasmine Patel","Location: Black Mountain","Time: 11 am to 6 pm", "Oncology Nurse",4, true, false));
        profiles.add(new Profile("Name: Taylor Mitchell", "Location: Glenrosa","Time: 7:30 am to 3:30 pm","Psychiatric Nurse",4, false, true));
        profiles.add(new Profile("Name: Olivia White", "Location: South Pandosy","Time: 10 am to 6 pm","Critical Care Nurse",5, true, false));
        profiles.add(new Profile("Name: Ethan Baker","Location: Upper Mission","Time: 8 am to 5 pm", "Neonatal Nurse",3, true, true));
        profiles.add(new Profile("Name: Aria Campbell","Location: Crawford","Time: 9 am to 4 pm", "Cardiac Nurse",4, false, false));


        // Add more profiles as needed
        return profiles;
    }
}