package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePassword extends AppCompatActivity {

    FirebaseDatabase firebasedatabase;
    FirebaseAuth firebaseAuth;
    TextView oldPw, newPw;
    Button cancel, savePw;
    String oldLPw, newLPw;

    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldPw = findViewById(R.id.etOldPw);
        newPw = findViewById(R.id.etNewPw);
        cancel = findViewById(R.id.btnCancel);
        savePw = findViewById(R.id.btnCfmChangePassword);
        firebaseAuth  = FirebaseAuth.getInstance();


    }

    public void change (View v)
    {
        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
        if(user != null)
        {
            user.updatePassword(newPw.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>()
            {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(getApplicationContext(),"Your password has been changed", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(ChangePassword.this, Login.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Your password could not be changed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}