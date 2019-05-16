package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class accountType extends AppCompatActivity {

    private Button userAccount;
    private Button userClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_account_type);
        getSupportActionBar().hide();

       userAccount = (Button)findViewById(R.id.btnPatient);
       userClient = (Button)findViewById(R.id.btnDoctor);

       userAccount.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(accountType.this, Login.class));
           }
       });

       userClient.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               startActivity(new Intent(accountType.this, LoginClient.class));
           }
       });
    }
}
