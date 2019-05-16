package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView register;
    private TextView forgetPassword;
    private FirebaseAuth firebaseAuth;
    String lName, lPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.login);
        getSupportActionBar().hide();
        Name = (EditText)findViewById(R.id.etEmail);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnForgetPassword);
        register =(TextView)findViewById(R.id.tVRegister);
        forgetPassword =(TextView)findViewById(R.id.tVForgotPassword);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();


        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Login.this, ForgetPassword.class));
            }
        });

        Login.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                if (validate()) {
                    //upload data to database
                    String user_email = Name.getText().toString().trim();
                    String user_password = Password.getText().toString().trim();

                    firebaseAuth.signInWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                //firebaseAuth.signOut();
                                //finish();
                                checkEmailVerification();
                                //startActivity(new Intent(Login.this, ProfileActivity.class));
                            } else {
                                Toast.makeText(Login.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

        });

    }

    private Boolean validate(){
        Boolean result = false;

        lName = Name.getText().toString();
        lPassword = Password.getText().toString();

        if ((lName.isEmpty() && lPassword.isEmpty()) || (lName.isEmpty() || lPassword.isEmpty())){
            Toast.makeText(this,"Please fill in all details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailFlag = firebaseUser.isEmailVerified();

        if(emailFlag){
            finish();
            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login.this, SecondActivity.class));
        }else{

            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

}
