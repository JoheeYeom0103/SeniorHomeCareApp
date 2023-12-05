package com.example.testingscrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button openFilterCareGiver, openFilterCareReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openFilterCareGiver = findViewById(R.id.openFilterCareGiver);
        openFilterCareGiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CareGiverPage.class);
                startActivity(intent);
            }
        });
        openFilterCareReceiver = findViewById(R.id.openFilterCareReceiver);
        openFilterCareReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CareReceiverPage.class);
                startActivity(intent);
            }
        });

    }
}