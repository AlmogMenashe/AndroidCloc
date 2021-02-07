package com.example.hitfirstapp.activitiys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hitfirstapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class MainRegister extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        mAuth = FirebaseAuth.getInstance();

    }

    public void RegisterFunc2 (View view){

        EditText RegisterUser= findViewById(R.id.RegisterUserText);
        final String email = RegisterUser.getText().toString();

        EditText RegisterPassword= findViewById(R.id.RegisterPasswordText);
        final String password = RegisterPassword.getText().toString();

        EditText RegisterPhone= findViewById(R.id.RegisterPhoneText);
        final String Phone = RegisterPhone.getText().toString();

        EditText RegisterAddress= findViewById(R.id.RegisterAddressText);
        final String Address = RegisterAddress.getText().toString();

       mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                          Toast.makeText(MainRegister.this, "Authentication ok2.",
                               Toast.LENGTH_SHORT).show();

//                          //FirebaseUser user = mAuth.getCurrentUser();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = user.getUid();

                            // Write a message to the database עובד לפי היררכיה
                            FirebaseDatabase database = FirebaseDatabase.getInstance(); // מביא את החיבור לfirebase
                            DatabaseReference myRef = database.getReference("Person").child(uid); // persons מביאה את הנתיב


                            Person p = new Person (email ,Phone ,Address);
                            myRef.setValue(p);

                            Intent intent= new Intent(MainRegister.this, MainActivity.class);
                            startActivity(intent);

//                            Toast.makeText(MainRegister.this,email +" "+ Phone+" "+ Address +" "  ,
//                                    Toast.LENGTH_LONG).show();


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainRegister.this, "Authentication failed." ,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

}