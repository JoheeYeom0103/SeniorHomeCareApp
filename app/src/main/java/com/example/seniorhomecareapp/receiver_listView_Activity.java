package com.example.seniorhomecareapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seniorhomecareapp.databinding.ActivityReceiverListViewBinding;

import java.util.ArrayList;
import java.util.List;

public class receiver_listView_Activity extends AppCompatActivity {
    private RecyclerView profileRecyclerView;
    private receiver_profile_Adapter receiverProfileAdapter;
    private boolean isHighlighted = false;
    ImageButton openFilterCareGiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_list_view);

        profileRecyclerView = findViewById(R.id.profileRecyclerView);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Populate with dummy data (replace this with real data retrieval)
        List<receiver_profile> dummyProfilesrec = createDummyProfiles();
        receiverProfileAdapter = new receiver_profile_Adapter(dummyProfilesrec);
        profileRecyclerView.setAdapter(receiverProfileAdapter);

        openFilterCareGiver = findViewById(R.id.imageView4);
        openFilterCareGiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //doesnt sent to Kevin page:
                Intent intent = new Intent(receiver_listView_Activity.this, CareGiverPage.class);
                startActivity(intent);
            }
        });

    }

    private List<receiver_profile> createDummyProfiles() {
        List<receiver_profile> profilesrec = new ArrayList<>();

        profilesrec.add(new receiver_profile("Name: Derick Galloway","Location: Rutland","Time: 3 am to 1 pm", "Physio Therapy / Mobility assistance"));
        profilesrec.add(new receiver_profile("Name: Elvira Serrano", "Location: Black Mountain","Time: 8 am to 3 pm","Medicine Management"));
        profilesrec.add(new receiver_profile("Name: Johnnie Irwin", "Location: Glenmore","Time: 8 am to 4 pm","making meals"));
        profilesrec.add(new receiver_profile("Name: Shana Mejia","Location: upper Mission","Time: 11 am to 5 pm", "All around Care"));
        profilesrec.add(new receiver_profile("Name: Marianne Harper","Location: Dilworth","Time: 9 am to 2 pm", "Companionship and Mobility Assistance"));
        profilesrec.add(new receiver_profile("Name: Leo Chang", "Location: Kettle Valley","Time: 10 am to 4 pm","Medication Assistance"));
        profilesrec.add(new receiver_profile("Name: Valerie Price","Location: Lower Mission","Time: 12 pm to 6 pm", "Meal Preparation and Companionship"));
        profilesrec.add(new receiver_profile("Name: Hector Mendoza","Location: Crawford","Time: 8 am to 5 pm", "Comprehensive Care and Emotional Support"));
        profilesrec.add(new receiver_profile("Name: Brenda Nguyen", "Location: Mission","Time: 9 am to 3 pm","Physical Therapy and Exercise Assistance"));
        profilesrec.add(new receiver_profile("Name: Isaac Jensen", "Location: Glenrosa","Time: 11 am to 6 pm","Medication Management and Companionship"));
        profilesrec.add(new receiver_profile("Name: Stella Carter","Location: Orchard Park","Time: 10 am to 4 pm", "Meal Preparation and Light Housekeeping"));
        profilesrec.add(new receiver_profile("Name: Roland Hayes","Location: South Pandosy","Time: 8 am to 5 pm", "Comprehensive Care and Leisure Activities"));

        // Add more profiles as needed
        return profilesrec;
    }
}
