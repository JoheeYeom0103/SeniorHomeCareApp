package com.example.seniorhomecareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class giverprofile_list extends AppCompatActivity implements giverProfile_adapter.OnLikedStateChangeListener {
    //Ken file!

    private RecyclerView profileRecyclerView;
    public giverProfile_adapter profileAdapter;
    private List<Profile> selectedProfiles; // Changed to instance variable
    private boolean isHighlighted = false;
    ImageButton openFilterCareGiver;
    ImageButton savelistButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giverprofile_list);

        profileRecyclerView = findViewById(R.id.GiverRecyclerView);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        selectedProfiles = new ArrayList<>(); // Initialize the selectedProfiles list

        // Populate with dummy data (replace this with real data retrieval)
        List<Profile> dummyProfiles = createDummyProfiles();
        profileAdapter = new giverProfile_adapter(dummyProfiles, this); // Pass the activity as the listener
        profileRecyclerView.setAdapter(profileAdapter);


        openFilterCareGiver = findViewById(R.id.imageView4);
        openFilterCareGiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //doesn't send to Kevin page:
                Intent intent = new Intent(giverprofile_list.this, receiver_profile_Adapter.class);
                startActivity(intent);
            }
        });
        /*
        openFilterCareGiver = findViewById(R.id.menu);
        openFilterCareGiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilterPage();
            }
        });

         */
    }
    /*
    public void openFilterPage() {
        Intent intent = new Intent(this, activity_filterGiver.class);
        startActivity(intent);
    }

     */

    public List<Profile> createDummyProfiles() {
        List<Profile> profiles = new ArrayList<>();

        profiles.add(new Profile("Name: John Doe","Location: Glenmore","Time: 7 am to 7 pm", "Registered Nurse",4,true, true));
        profiles.add(new Profile("Name: George Mcneil", "Location: Lower Mission","Time: 7 am to 3 pm","Certified Nurse Assistant",5, false, true));
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

    // Implementation of the OnLikedStateChangeListener
    @Override
    public void onLikedStateChanged(Profile profile, boolean isLiked) {
        // Handle the liked state change here
        if (isLiked) {
            // Add the profile data to your filtered list or perform other actions
            // In this example, we'll print the liked profile's name
            System.out.println("Liked Profile: " + profile.getName());
        } else {
            // Remove the profile data from your filtered list or perform other actions
            // In this example, we'll print the unliked profile's name
            System.out.println("Unliked Profile: " + profile.getName());
        }
    }
}
