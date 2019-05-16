package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MedView_Config {

        private Context mContext;
        private MedicationAdapter medicationAdapter;
        private Button setEndDate_Btn;
        private FirebaseAuth firebaseAuth;
        public void setConfig(RecyclerView recyclerView, Context context, List<Medication> medications, List<String> keys){
            mContext = context;
            medicationAdapter = new MedicationAdapter(medications, keys);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(medicationAdapter);


        }

        class MedicationItemView extends RecyclerView.ViewHolder{
            private TextView startDate;
            private TextView medDose;
            private TextView medIntl;
            private TextView medName;
            private TextView endDate;
            private String key;

            public MedicationItemView(ViewGroup parent) {
                super(LayoutInflater.from(mContext).inflate(R.layout.activity_med_view_config, parent, false));
                startDate = (TextView) itemView.findViewById(R.id.start_date);
                endDate = (TextView) itemView.findViewById(R.id.time_txtView);
                medName = (TextView) itemView.findViewById(R.id.med_name);
                medDose = (TextView) itemView.findViewById(R.id.med_dose);
                medIntl = (TextView) itemView.findViewById(R.id.med_intl);

                 itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, MedEnd.class);
                        intent.putExtra("key", key);
                        intent.putExtra("startDate", startDate.getText().toString());
                        intent.putExtra("endDate", endDate.getText().toString());
                        intent.putExtra("medName", medName.getText().toString());
                        intent.putExtra("medDose", medDose.getText().toString());
                        intent.putExtra("medIntl", medIntl.getText().toString());

                        mContext.startActivity(intent);

                    }
                });

            }

            public void bind(Medication medication, String key) {
                startDate.setText(medication.getStartDate());
                endDate.setText(medication.getEndDate());
                medName.setText(medication.getMedName());
                medDose.setText(medication.getMedDose());
                medIntl.setText(medication.getMedIntl());
                this.key = key;
            }
        }



        class MedicationAdapter extends RecyclerView.Adapter<MedicationItemView>{
            private List<Medication> medicationList;
            private List<String> mKeys;

            public MedicationAdapter(List<Medication> medicationList, List<String> mKeys) {
                this.medicationList = medicationList;
                this.mKeys = mKeys;
            }

            @NonNull
            @Override
            public MedicationItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new MedicationItemView(viewGroup);
            }

            @Override
            public void onBindViewHolder(@NonNull MedicationItemView medicationItemView, int i) {
                medicationItemView.bind(medicationList.get(i),mKeys.get(i));
            }

            @Override
            public int getItemCount() {
                return medicationList.size();
            }
        }
}


