package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class AppointmentList extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Button scheduleAppointment, appointmentHistory;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        setContentView(R.layout.activity_appointment_list);
        getSupportActionBar().hide();


        scheduleAppointment = (Button) findViewById(R.id.scheduleAppBtn);
        appointmentHistory = (Button) findViewById(R.id.AppHistoryBtn);
        scheduleAppointment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openScheduleAppointmentActivity();
            }
        });

        appointmentHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppointmentHistoryActivity();
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_appointments);
        new FirebaseDatabaseHelper().readAppointments(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Appointment> appointments, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, AppointmentList.this, appointments, keys);
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
        }, "upcoming",user.getUid());

    }

    public void openScheduleAppointmentActivity(){
        Intent intent = new Intent(this, ScheduleAppointment.class);
        startActivity(intent);
    }

    public void openAppointmentHistoryActivity(){
        Intent intent = new Intent (this, AppointmentHistory.class);
        startActivity(intent);
    }
}