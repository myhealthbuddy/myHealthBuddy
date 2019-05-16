package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity {

    private EditText updateUser, updateEmail, updateAge, updateDOB, updateMobileNumber;
    private Button save;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        getSupportActionBar().hide();

        updateUser = findViewById(R.id.etEUser);
        updateEmail = findViewById(R.id.etEEmail);
        updateAge = findViewById(R.id.etEAge);
        updateDOB = findViewById(R.id.etEDOB);
        updateMobileNumber = findViewById(R.id.etEMobileNumber);
        save = findViewById(R.id.btnSave);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.child("Users").child(user.getUid()).getValue(UserProfile.class);
                updateUser.setText(userProfile.getName());
                updateAge.setText(userProfile.getAge());
                updateDOB.setText(userProfile.getDateOfBirth());
                updateEmail.setText(userProfile.getEmail());
                updateMobileNumber.setText(userProfile.getMobileNumber());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = updateUser.getText().toString();
                    String age = updateAge.getText().toString();
                    String dob = updateDOB.getText().toString();
                    String email = updateEmail.getText().toString();
                    String mobileNumber = updateMobileNumber.getText().toString();


                    UserProfile userProfile = new UserProfile(age, email, name, dob, mobileNumber);
                    databaseReference.child("Users").child(user.getUid()).setValue(userProfile);

                    finish();
                }
            });
    }

}
