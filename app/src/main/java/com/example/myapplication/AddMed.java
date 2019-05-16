package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class AddMed extends AppCompatActivity {
    private Button button;
    private EditText medi, dose, intl;
    private String mname, mdose, mintl, id;
    private FirebaseAuth firebaseAuth;
    private ArrayList<Integer> AlarmDays = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);

        final String sdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        id = user.getUid();
        medi = (EditText)findViewById(R.id.medname);
        dose = (EditText)findViewById(R.id.meddose);
        intl = (EditText)findViewById(R.id.intlhr);

        AlarmDays.add(Calendar.SUNDAY);
        AlarmDays.add(Calendar.MONDAY);
        AlarmDays.add(Calendar.TUESDAY);
        AlarmDays.add(Calendar.WEDNESDAY);
        AlarmDays.add(Calendar.THURSDAY);
        AlarmDays.add(Calendar.FRIDAY);
        AlarmDays.add(Calendar.SATURDAY);

        button = findViewById(R.id.storeMed);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mname = medi.getText().toString();
                mdose = dose.getText().toString();
                mintl = intl.getText().toString();
                String addinfo = mname + ", " + mdose;

                Medication medication = new Medication();
                medication.setStartDate(sdate);
                medication.setMedName(mname);
                medication.setMedDose(mdose);
                medication.setMedIntl(mintl);
                medication.setEndDate("Current");
                medication.setuID(id);

                new MedicationToFirebase().addMedications(medication, new MedicationToFirebase.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Medication> medications, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        //Toast.makeText(QRscanner.this, "This application submitted successfully", Toast.LENGTH_LONG).show();
                        //startActivity(new Intent(QRscanner.this, MedHistory.class));
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });


                int medintl = Integer.parseInt(String.valueOf(mintl));
                if(medintl==1){
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, 13);
                    //Add Medication name and dose
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);
                    //Set Alarm Days
                    intent.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);
                    startActivity(intent);
                }
                else if(medintl==2){
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    Intent intent2 = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, 10);
                    intent2.putExtra(AlarmClock.EXTRA_HOUR, 22);
                    //Add Medication name and dose
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);
                    intent2.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);
                    //Set Alarm days
                    intent.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);
                    intent2.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);

                    startActivity(intent);
                    startActivity(intent2);
                }else if(medintl==3){
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    Intent intent2 = new Intent(AlarmClock.ACTION_SET_ALARM);
                    Intent intent3 = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, 10);
                    intent2.putExtra(AlarmClock.EXTRA_HOUR, 15);
                    intent3.putExtra(AlarmClock.EXTRA_HOUR,20);
                    //Add Medication name and dose
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);
                    intent2.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);
                    intent3.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);

                    intent.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);
                    intent2.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);
                    intent3.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);

                    startActivity(intent);
                    startActivity(intent2);
                    startActivity(intent3);
                }else if(medintl==4){
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    Intent intent2 = new Intent(AlarmClock.ACTION_SET_ALARM);
                    Intent intent3 = new Intent(AlarmClock.ACTION_SET_ALARM);
                    Intent intent4 = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, 8);
                    intent2.putExtra(AlarmClock.EXTRA_HOUR, 12);
                    intent3.putExtra(AlarmClock.EXTRA_HOUR,16);
                    intent4.putExtra(AlarmClock.EXTRA_HOUR, 20);
                    //Add Medication name and dose
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);
                    intent2.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);
                    intent3.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);
                    intent4.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);

                    intent.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);
                    intent2.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);
                    intent3.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);
                    intent4.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);


                    startActivity(intent);
                    startActivity(intent2);
                    startActivity(intent3);
                    startActivity(intent4);
                }else if(medintl==5){
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    Intent intent2 = new Intent(AlarmClock.ACTION_SET_ALARM);
                    Intent intent3 = new Intent(AlarmClock.ACTION_SET_ALARM);
                    Intent intent4 = new Intent(AlarmClock.ACTION_SET_ALARM);
                    Intent intent5 = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, 8);
                    intent2.putExtra(AlarmClock.EXTRA_HOUR, 11);
                    intent3.putExtra(AlarmClock.EXTRA_HOUR,14);
                    intent4.putExtra(AlarmClock.EXTRA_HOUR, 17);
                    intent5.putExtra(AlarmClock.EXTRA_HOUR, 20);
                    //Add Medication name and dose
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);
                    intent2.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);
                    intent3.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);
                    intent4.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);
                    intent5.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);

                    intent.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);
                    intent2.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);
                    intent3.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);
                    intent4.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);
                    intent5.putExtra(AlarmClock.EXTRA_DAYS, AlarmDays);

                    startActivity(intent);
                    startActivity(intent2);
                    startActivity(intent3);
                    startActivity(intent4);
                    startActivity(intent5);
                }
            }

        });
    }



}
