package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import java.util.Calendar;
import java.util.List;

public class AppointmentDetail extends AppCompatActivity {

    private static final String TAG = "AppointmentListActivity";

    private Spinner mSpeciality_spinner;
    private Spinner mLocation_spinner;
    private Spinner mTime_spinner;
    private TextView mDate_textView;
    private EditText mDoctor_editText;
    private EditText mDescription_editText;

    private FirebaseAuth firebaseAuth;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private Button rescheduleBtn;
    private Button deleteBtn;
    private Button backBtn;

    private String key;
    private String speciality;
    private String date;
    private String time;
    private String location;
    private String doctor;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_detail);

        firebaseAuth = FirebaseAuth.getInstance();

        key = getIntent().getStringExtra("key");
        speciality = getIntent().getStringExtra("speciality");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        location = getIntent().getStringExtra("location");
        doctor = getIntent().getStringExtra("doctor");
        description = getIntent().getStringExtra("description");

        mSpeciality_spinner = (Spinner) findViewById(R.id.spinnerSpeciality);
        mLocation_spinner = (Spinner) findViewById(R.id.spinnerLocation);
        mTime_spinner = (Spinner) findViewById(R.id.spinnerTime);
        mDate_textView = (TextView) findViewById(R.id.dateTxtView);
        mDoctor_editText = (EditText) findViewById(R.id.editTextDoctor);
        mDescription_editText = (EditText) findViewById(R.id.editTextDescription);

        mSpeciality_spinner.setSelection(get_Index_SpinnerItem(mSpeciality_spinner, speciality));
        mLocation_spinner.setSelection(get_Index_SpinnerItem(mLocation_spinner, location));
        mTime_spinner.setSelection(get_Index_SpinnerItem(mTime_spinner, time));
        mDoctor_editText.setText(doctor);
        mDescription_editText.setText(description);
        mDate_textView.setText(date);

        rescheduleBtn = (Button) findViewById(R.id.rescheduleBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        backBtn = (Button) findViewById(R.id.backBtn);

        mDisplayDate = findViewById(R.id.dateTxtView);

        mDisplayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 0);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AppointmentDetail.this, AlertDialog.THEME_HOLO_LIGHT, mDateSetListener, year,month,day);
                dialog.getWindow();
                dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + dayOfMonth + "/" + month + "/" + year );
                String date = dayOfMonth + "/" + month + "/" + year ;
                mDisplayDate.setText(date);
            }
        };

        rescheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FirebaseUser user = firebaseAuth.getCurrentUser();

                Appointment appointment = new Appointment();
                appointment.setSpeciality(mSpeciality_spinner.getSelectedItem().toString());
                appointment.setLocation(mLocation_spinner.getSelectedItem().toString());
                appointment.setTime(mTime_spinner.getSelectedItem().toString());
                appointment.setDate(mDate_textView.getText().toString());
                appointment.setDoctor(mDoctor_editText.getText().toString());
                appointment.setDescription(mDescription_editText.getText().toString());

                appointment.setuID(user.getUid());

                new FirebaseDatabaseHelper().rescheduleAppointment(key, appointment, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Appointment> appointments, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(AppointmentDetail.this, "Appointment record has been reschedule successfully", Toast.LENGTH_LONG).show();
                        finish(); return;
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteAppointment(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Appointment> appointments, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(AppointmentDetail.this, "Appointment record has been deleted successfully", Toast.LENGTH_LONG).show();
                        finish(); return;
                    }
                });
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); return;
            }
        });

    }

    private int get_Index_SpinnerItem(Spinner spinner, String item){
        int index = 0;
        for (int i = 0; i<spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).equals(item)){
                index = i;
                break;
            }
        }
        return index;
    }
}
