package com.example.hitfirstapp.activitiys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainLogin extends AppCompatActivity {


    private FirebaseAuth mAuth;

    SharedPreferences sharedPreferences; // הגדרה גלובלית
    EditText user;
    EditText pass;
    int Numberofentries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        mAuth = FirebaseAuth.getInstance();

        sharedPreferences = getPreferences(MODE_PRIVATE); // שומר את הנתונים במחשב במוד פרטי
        user = findViewById(R.id.emailText);
        pass = findViewById(R.id.passwordText);
        if(sharedPreferences.getString("KeyUser", null) != null){ // לשמירת עוגיות
            user.setText(sharedPreferences.getString("KeyUser", null));
            pass.setText(sharedPreferences.getString("KeyPass", null));

            Intent intent= new Intent(MainLogin.this, MainActivity.class); // קפיצה למסך רישום
            startActivity(intent);




        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void RegisterFunc(View view){ //פונקצית הרישום

        EditText emailText= findViewById(R.id.emailText);
        String email = emailText.getText().toString();

        EditText passText= findViewById(R.id.passwordText);
        String password = passText.getText().toString();

        Intent intent= new Intent(MainLogin.this, MainRegister.class); // קפיצה למסך רישום
        startActivity(intent);

    }

    public void LoginFunc(View view){  //פונקצית החיבור

        EditText emailText = findViewById(R.id.emailText);
        String email = emailText.getText().toString();
        user = findViewById(R.id.emailText); //לשמירת עוגיות


        EditText passText = findViewById(R.id.passwordText);
        String password = passText.getText().toString();
        pass = findViewById(R.id.passwordText); //לשמירת עוגיות


        SharedPreferences.Editor editor = sharedPreferences.edit(); // כל אקטיביטי רואה אותו

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainLogin.this, "login ok.",
                                    Toast.LENGTH_SHORT).show();

                            editor.putString("KeyUser" , user.getText().toString()); // לשמור את היוזר עצמו
                            editor.putString("KeyPass" , pass.getText().toString()); // לשמור את הסיסמה
                            editor.apply();


                            Intent intent= new Intent(MainLogin.this, MainActivity.class); // קפיצה למסך רישום
                            startActivity(intent);
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainLogin.this, "login failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    /*
    מחלקה לטובת עוגיות
    public void func4(View view){

        EditText user = findViewById(R.id.emailText);
        EditText pass = findViewById(R.id.passwordText);

        SharedPreferences.Editor editor = sharedPreferences.edit(); // כל אקטיביטי רואה אותו
        editor.putString("KeyUser" , user.getText().toString()); // לשמור את היוזר עצמו
        editor.putString("KeyPuss" , pass.getText().toString()); // לשמור את הסיסמה
        editor.apply();

        Intent intent= new Intent(MainLogin.this, MainRegister.class); // קפיצה למסך רישום
        startActivity(intent);


    }
*/
   // ctrl + o // כל הפונקציות
    

}