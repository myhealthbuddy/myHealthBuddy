package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
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

public class ScheduleAppointment extends AppCompatActivity {

    private static final String TAG = "AppointmentListActivity";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private FirebaseAuth firebaseAuth;

    private Spinner mSpeciality_spinner;
    private Spinner mLocation_spinner;
    private Spinner mTime_spinner;
    private EditText mDoctor_editText;
    private EditText mDescription_editText;
    private TextView mDate_textView;

    private Button submitBtn;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);

        firebaseAuth = FirebaseAuth.getInstance();

        mDisplayDate = findViewById(R.id.dateTxtView);

        mDisplayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 0);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(ScheduleAppointment.this, AlertDialog.THEME_HOLO_LIGHT , mDateSetListener, year,month,day);
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

        mSpeciality_spinner = (Spinner) findViewById(R.id.spinnerSpeciality);
        mLocation_spinner = (Spinner) findViewById(R.id.spinnerLocation);
        mTime_spinner = (Spinner) findViewById(R.id.spinnerTime);
        mDate_textView = (TextView) findViewById(R.id.dateTxtView);
        mDoctor_editText = (EditText) findViewById(R.id.editTextDoctor);
        mDescription_editText = (EditText) findViewById(R.id.editTextDescription);

        submitBtn = (Button) findViewById(R.id.rescheduleBtn);
        backBtn = (Button) findViewById(R.id.backBtn);

        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Appointment appointment = new Appointment();
                if (mDate_textView.getText().toString().equals("")){
                    Toast.makeText(ScheduleAppointment.this, "Please fill in the date", Toast.LENGTH_LONG).show();
                }
                else if (mSpeciality_spinner.getSelectedItem().toString().equals("Please select a service…")){
                    Toast.makeText(ScheduleAppointment.this, "Please select a speciality", Toast.LENGTH_LONG).show();
                }
                else if (mLocation_spinner.getSelectedItem().toString().equals("Preferred Location")){
                    Toast.makeText(ScheduleAppointment.this, "Please select your preferred location", Toast.LENGTH_LONG).show();
                }
                else if (mTime_spinner.getSelectedItem().toString().equals("Preferred Time")){
                    Toast.makeText(ScheduleAppointment.this, "please select your preferred time", Toast.LENGTH_LONG).show();
                }
                //else if (mDescription_editText.getText().toString().equals("")){
                    //Toast.makeText(ScheduleAppointment.this, "please provide a brief description", Toast.LENGTH_LONG).show();
                //}
                else {
                    final FirebaseUser user = firebaseAuth.getCurrentUser();

                    appointment.setDate(mDate_textView.getText().toString());
                    appointment.setSpeciality(mSpeciality_spinner.getSelectedItem().toString());
                    appointment.setLocation(mLocation_spinner.getSelectedItem().toString());
                    appointment.setTime(mTime_spinner.getSelectedItem().toString());
                    appointment.setDescription(mDescription_editText.getText().toString());
                    appointment.setDoctor(mDoctor_editText.getText().toString());
                    appointment.setuID(user.getUid());

                    new FirebaseDatabaseHelper().addAppointment(appointment, new FirebaseDatabaseHelper.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<Appointment> appointments, List<String> keys) {

                        }

                        @Override
                        public void DataIsInserted() {
                            Toast.makeText(ScheduleAppointment.this, "This application submitted successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ScheduleAppointment.this, AppointmentList.class));
                        }

                        @Override
                        public void DataIsUpdated() {

                        }

                        @Override
                        public void DataIsDeleted() {

                        }
                    });
                }

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish(); return;
            }
        });


    }

}

