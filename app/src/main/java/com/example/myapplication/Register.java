package com.example.myapplication;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {

    private EditText name, password, email, dob, mobileNumber, Age;
    private Button regButton;
    private TextView userLogin;
    String rAge, rName, rPassword, rEmail, rDOB, rMobileNumber;
    private FirebaseAuth firebaseAuth;
    private List<UserProfile> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.register);
        getSupportActionBar().hide();
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    //upload data to database
                    String user_email = email.getText().toString().trim();
                    String user_password = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendUserData();
                                sendEmailVerification();
                                //Toast.makeText(Register.this, "Register Successful", Toast.LENGTH_SHORT).show();
                                //firebaseAuth.signOut();
                                //finish();
                                //startActivity(new Intent(Register.this, Login.class));
                            } else {
                                Toast.makeText(Register.this, "Register Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });


    }

    private void setupUIViews(){
        name = (EditText)findViewById(R.id.etUserRegister);
        password = (EditText)findViewById(R.id.etPwRegister);
        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tVReturn);
        email = (EditText)findViewById(R.id.etEmailRegister);
        Age = (EditText)findViewById(R.id.etAge);
        dob = (EditText)findViewById(R.id.etDOB);
        mobileNumber = (EditText)findViewById(R.id.etMobileNumber);
    }

    private Boolean validate(){
        Boolean result = false;

        rName = name.getText().toString();
        rPassword = password.getText().toString();
        rEmail = email.getText().toString();
        rDOB = dob.getText().toString();
        rMobileNumber = mobileNumber.getText().toString();
        rAge = Age.getText().toString();

        if (rName.isEmpty() && rPassword.isEmpty() && rEmail.isEmpty() && rDOB.isEmpty() && rMobileNumber.isEmpty()){
            Toast.makeText(this,"Please fill in all details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this, "Successfully Registered, Email verification sent!",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(Register.this, Login.class));
                    }else{
                        Toast.makeText(Register.this, "Verification mail has not been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        DatabaseReference databaseReference= firebaseDatabase.getReference();
        UserProfile userProfile = new UserProfile(rAge, rEmail, rName, rDOB, rMobileNumber);
        databaseReference.child("Users").child(user.getUid()).setValue(userProfile);
    }
}
