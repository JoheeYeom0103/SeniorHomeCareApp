package com.example.seniorhomecareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ReceiverProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiverprofile);


        // Ken stuff
        ImageButton listviewButtonGiver = findViewById(R.id.listviewButton_receiver);
        listviewButtonGiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListView_receiver();
            }
        });
        //end of ken fragment
    }

    public void openListView_receiver() {
        Intent intent = new Intent(this,receiver_listView_Activity.class);
        startActivity(intent);
    }

    public void openProfile_receiver() {

    }

    public void openSaveList_receiver() {

    }

    public void editBanner_receiver() {

    }

    public void editBio_receiver() {

    }

    public void next1_receiver() {

    }
}
