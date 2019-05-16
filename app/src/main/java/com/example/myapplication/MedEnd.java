package com.example.myapplication;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MedEnd extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextView sdate, edate, mname, mdose, mintl;
    private Button btn;
    private String key, sDate, eDate, mName, mDose, mIntl, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_end);

        firebaseAuth = FirebaseAuth.getInstance();
        final String EndDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        key = getIntent().getStringExtra("key");
        sDate = getIntent().getStringExtra("startDate");
        eDate = getIntent().getStringExtra("endDate");
        mName = getIntent().getStringExtra("medName");
        mDose = getIntent().getStringExtra("medDose");
        mIntl = getIntent().getStringExtra("medIntl");

        sdate = findViewById(R.id.sdate);
        edate = findViewById(R.id.edate);
        mname = findViewById(R.id.mname);
        mdose = findViewById(R.id.mdose);
        mintl = findViewById(R.id.mintl);

        //if(key!=null) {
        sdate.setText(sDate);
        edate.setText(eDate);
        mname.setText(mName);
        mdose.setText(mDose);
        mintl.setText(mIntl);
        //}




        btn = (Button) findViewById(R.id.setEndDate_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                Medication medication = new Medication();

                    medication.setStartDate(sDate);
                    medication.setMedName(mName);
                    medication.setMedDose(mDose);
                    medication.setMedIntl(mIntl);
                    medication.setEndDate(EndDate);
                    medication.setuID(user.getUid());

                new MedicationToFirebase().updateMedication(key, medication, new MedicationToFirebase.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Medication> medications, List<String> keys) {

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
                });
            }
        });
    }
}
