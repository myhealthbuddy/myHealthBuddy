package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;

    private DatabaseReference mReferenceAppointments;
    private List<Appointment> appointments = new ArrayList<>();


    public interface DataStatus{
        void DataIsLoaded(List<Appointment> appointments, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceAppointments = mDatabase.getReference("appointments");



    }

    public void readAppointments(final DataStatus dataStatus, String type,String user){
        final String type1=type;
        final String user1=user;
        mReferenceAppointments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                appointments.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {

                    Appointment appointment = keyNode.getValue(Appointment.class);
                    if (appointment.getuID().equals(user1)) {
                        String strappointmentDate = appointment.getDate();
                        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");

                        Date currentdate = null;
                        Date appointmentdate = null;

                        try {
                            appointmentdate = format.parse(strappointmentDate);
                            currentdate = format.parse(format.format(new Date()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (type1.equals("upcoming")) {
                            if (appointmentdate.after(currentdate)) {
                                appointments.add(appointment);
                                keys.add(keyNode.getKey());
                            }
                        } else if (type1.equals("history")) {
                            if (appointmentdate.before(currentdate)) {
                                appointments.add(appointment);
                                keys.add(keyNode.getKey());
                            }

                        }

                    }
                }

                sortAppointment(appointments, keys);
                dataStatus.DataIsLoaded(appointments, keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void addAppointment(Appointment appointment, final DataStatus dataStatus){

        String key = mReferenceAppointments.push().getKey();
        mReferenceAppointments.child(key).setValue(appointment).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }

    public void rescheduleAppointment (String key, Appointment appointment, final DataStatus dataStatus){
        mReferenceAppointments.child(key).setValue(appointment).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deleteAppointment (String key, final DataStatus dataStatus){
        mReferenceAppointments.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }

    public void sortAppointment(List<Appointment> appointments,List<String> keys) {

        SimpleDateFormat format=new SimpleDateFormat("dd/mm/yyyy");

        int i, j, min_idx;

        // One by one move boundary of unsorted subarray
        for (i = 0; i < appointments.size()-1; i++)
        {
            // Find the minimum element in unsorted array
            min_idx = i;
            for (j = i+1; j < appointments.size(); j++) {

                String strdate1 = appointments.get(j).getDate();
                String strdate2 = appointments.get(min_idx).getDate();

                Date date1 = null;
                Date date2 = null;
                try {
                    date1 = format.parse(strdate1);
                    date2 = format.parse(strdate2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (date1.compareTo(date2) <= 0){
                    min_idx = j;
                }

            }
            Appointment temp = appointments.get(min_idx);
            String tempkey=keys.get(min_idx);

            appointments.set(min_idx,appointments.get(i));
            keys.set(min_idx,keys.get(i));

            appointments.set(i,temp);
            keys.set(i,tempkey);
        }
    }
}
