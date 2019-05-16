package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QRscanner extends AppCompatActivity {
    TextView qrcodeResult, medresult, doseresult, intlresult, errorMessage;
    String uid, uage, mname, mdose, minterval;
    private String id;
    private View v;
    private Button b1;
    private FirebaseAuth firebaseAuth;
    private ArrayList<Integer> AlarmDays = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        final String sdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        id = user.getUid();

        AlarmDays.add(Calendar.SUNDAY);
        AlarmDays.add(Calendar.MONDAY);
        AlarmDays.add(Calendar.TUESDAY);
        AlarmDays.add(Calendar.WEDNESDAY);
        AlarmDays.add(Calendar.THURSDAY);
        AlarmDays.add(Calendar.FRIDAY);
        AlarmDays.add(Calendar.SATURDAY);

        //qrcodeResult = (TextView)findViewById(R.id.qrcode_result);
        medresult = (TextView)findViewById(R.id.result_med);
        doseresult = (TextView)findViewById(R.id.result_dose);
        intlresult = (TextView)findViewById(R.id.result_intl);
        errorMessage = (TextView)findViewById(R.id.error_message);
        b1 = (Button)findViewById(R.id.add_medication);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Medication medication = new Medication();
            if(id.contentEquals(uid)){
                String addinfo = mname + ", " + mdose;
                int medintl = Integer.parseInt(minterval);

                medication.setStartDate(sdate);
                medication.setMedName(mname);
                medication.setMedDose(mdose);
                medication.setMedIntl(minterval);
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

                if(medintl==1){
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, 13);
                    //Add Medication name and dose
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, addinfo);

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

            }else if(uid.isEmpty()){
                errorMessage.setText("Please scan QR Code for precribed medication.");
            }else{
                errorMessage.setText("This medication was prescribed for another patient.");
            }
            }
        });
/*        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMed();
            }
        }); */

    }

/*    public void addMed(){

        //Intent intent = new Intent(this, AddMed.class);
        Intent intent = new Intent();
        intent.putExtra("TextValue", str);
        intent.setClass(QRscanner.this, AddMed.class);
        startActivity(intent);
    } */

    public void scanBarcode(View v){
        this.v = v;
        Intent intent = new Intent(this, preview.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0){
            if(resultCode== CommonStatusCodes.SUCCESS){
                if(data !=null){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    // qrcodeResult.setText(barcode.displayValue);
                    //qrcodeResult.setText(barcode.rawValue);
                    final String stext = barcode.rawValue;
                    //qrcodeResult.setText(stext);
                    String str = stext;
                    String[] arr= str.split("\n");
                    uid = arr[0];
                    mname = arr[1];
                    mdose = arr[2];
                    minterval = arr[3];
                    medresult.setText(mname);
                    doseresult.setText(mdose);
                    intlresult.setText(minterval);

                }
                else{
                    medresult.setText("No QR Code found");
                }
            }
            else{
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }


}
