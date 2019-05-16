package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MedicationActivity extends AppCompatActivity {
    private Button button, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        //QR code button
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRscanner();
            }
        });
        // History button

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMediHistory();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddMed();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMedAlarm();
            }
        });

    }

    public void openQRscanner() {
        Intent intent = new Intent(this, QRscanner.class);
        startActivity(intent);
    }

    public void openMediHistory(){
        Intent intent = new Intent(this, MedHistory.class);
        startActivity(intent);
    }

    public void openAddMed(){
        Intent intent = new Intent(this, AddMed.class);
        startActivity(intent);
    }

    public void openMedAlarm(){
        Intent intent = new Intent(this, MedAlarm.class);
        startActivity(intent);
    }
}
