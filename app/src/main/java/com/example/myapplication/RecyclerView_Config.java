package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class RecyclerView_Config {
    private Context mContext;
    private AppointmentsAdapter mAppointmentsAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Appointment> appointments, List<String> keys){
        mContext = context;
        mAppointmentsAdapter = new AppointmentsAdapter(appointments, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAppointmentsAdapter);
    }


    class AppointmentItemView extends RecyclerView.ViewHolder{
        private TextView mDate;
        private TextView mSpeciality;
        private TextView mLocation;
        private TextView mTime;
        private TextView mDoctor;
        private TextView mDescription;

        private String key;

        public AppointmentItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.appointment_list_item, parent, false));

            mDate = (TextView) itemView.findViewById(R.id.date_txtView);
            mTime = (TextView) itemView.findViewById(R.id.time_txtView);
            mSpeciality = (TextView) itemView.findViewById(R.id.speciality_txtView);
            mLocation = (TextView) itemView.findViewById(R.id.location_txtView);
            mDoctor = (TextView) itemView.findViewById(R.id.mDoctor_editText);
            mDescription = (TextView) itemView.findViewById(R.id.mDescription_editText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, AppointmentDetail.class);
                    intent.putExtra("key", key);
                    intent.putExtra("speciality", mSpeciality.getText().toString());
                    intent.putExtra("date", mDate.getText().toString());
                    intent.putExtra("location", mLocation.getText().toString());
                    intent.putExtra("time", mTime.getText().toString());
                    intent.putExtra("doctor", mDoctor.getText().toString());
                    intent.putExtra("description", mDescription.getText().toString());

                    mContext.startActivity(intent);

                }
            });
        }

        public void bind(Appointment appointment, String key){
            mDate.setText(appointment.getDate());
            mSpeciality.setText(appointment.getSpeciality());
            mLocation.setText(appointment.getLocation());
            mTime.setText(appointment.getTime());
            mDoctor.setText(appointment.getDoctor());
            mDescription.setText(appointment.getDescription());
            this.key = key;
        }
    }

    class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentItemView>{
        private List<Appointment> mAppointmentList;
        private List<String> mKeys;

        public AppointmentsAdapter(List<Appointment> mAppointmentList, List<String> mKeys) {
            this.mAppointmentList = mAppointmentList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public AppointmentItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new AppointmentItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull AppointmentItemView appointmentItemView, int i) {
            appointmentItemView.bind(mAppointmentList.get(i), mKeys.get(i));

        }

        @Override
        public int getItemCount() {
            return mAppointmentList.size();
        }
    }

}
