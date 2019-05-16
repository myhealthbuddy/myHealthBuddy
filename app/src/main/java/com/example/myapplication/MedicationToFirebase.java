package com.example.myapplication;
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

public class MedicationToFirebase {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceMedications;
    private List<Medication> medications = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Medication> medications, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public MedicationToFirebase() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceMedications = mDatabase.getReference("medications");

    }

    public void readMedication(final DataStatus dataStatus, String type, String user){
        final String type1=type;
        final String user1=user;
        mReferenceMedications.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                medications.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {

                    //Medication medication = new Medication(keyNode.child("startDate").toString(), "test", keyNode.child("medDose").toString(),keyNode.child("medIntl").toString(), keyNode.child("endDate").toString(), keyNode.child("uID").toString());
                    Medication medication = keyNode.getValue(Medication.class);
                    if (medication.getuID().equals(user1)) {
                        medications.add(medication);
                        keys.add(keyNode.getKey());

                       }
                }
//                sortMedication(medications, keys);
                dataStatus.DataIsLoaded(medications, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void addMedications(Medication medication, final DataStatus dataStatus){

        String key = mReferenceMedications.push().getKey();
        mReferenceMedications.child(key).setValue(medication).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }

    public void updateMedication (String key, Medication medication, final MedicationToFirebase.DataStatus dataStatus){
        mReferenceMedications.child(key).setValue(medication).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void sortMedication(List<Medication> medications,List<String> keys) {

        SimpleDateFormat format=new SimpleDateFormat("dd/mm/yyyy");

        int i, j, min_idx;

        // One by one move boundary of unsorted subarray
        for (i = 0; i < medications.size()-1; i++)
        {
            // Find the minimum element in unsorted array
            min_idx = i;
            for (j = i+1; j < medications.size(); j++) {

                String strdate1 = medications.get(j).getStartDate();
                String strdate2 = medications.get(min_idx).getStartDate();

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
            Medication temp = medications.get(min_idx);
            String tempkey=keys.get(min_idx);

            medications.set(min_idx,medications.get(i));
            keys.set(min_idx,keys.get(i));

            medications.set(i,temp);
            keys.set(i,tempkey);
        }
    }

}
