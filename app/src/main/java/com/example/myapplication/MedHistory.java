package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MedHistory extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_med_history);
        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_medHistory);
        new MedicationToFirebase().readMedication(new MedicationToFirebase.DataStatus() {
            @Override
            public void DataIsLoaded(List<Medication> medications, List<String> keys) {
                new MedView_Config().setConfig(mRecyclerView, MedHistory.this, medications, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        }, "history", user.getUid());
    }
}
