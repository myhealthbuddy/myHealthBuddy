package com.example.myapplication;

import android.app.ProgressDialog;
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

public class LoginClient extends AppCompatActivity {

    private EditText cName;
    private EditText cPassword;
    private Button cLogin;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login_client);
        getSupportActionBar().hide();
        cName = (EditText)findViewById(R.id.etCUsername);
        cPassword = (EditText)findViewById(R.id.etCPassword);
        cLogin = (Button)findViewById(R.id.btnCLogin);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();


        cLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(cName.getText().toString(), cPassword.getText().toString());
            }
        });

        }

    private void validate (String cUserName, String cUserPassword) {

        firebaseAuth.signInWithEmailAndPassword(cUserName, cUserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginClient.this,"Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginClient.this,MapsActivity.class));
                }else{
                    Toast.makeText(LoginClient.this,"Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
