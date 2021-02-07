package com.example.hitfirstapp.activitiys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hitfirstapp.R;
import com.example.hitfirstapp.fragments.FragmentClac;
import com.example.hitfirstapp.fragments.FragmentScClac;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.Math.*;


public class MainActivity extends AppCompatActivity {

    String  opString ;
    TextView textView;
    double num1,num2;
    char op;

    private final String KEY = "MainActivityKeyName" ; // מפתח
    private FirebaseAuth mAuth;
    private FragmentManager FragmentManager; //הגדרה לפרגמנט

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        textView=findViewById(R.id.textViewResult);
        textView.setText("");

        String str = textView.getText().toString();
        funcGetFromDB();

        FragmentManager = getSupportFragmentManager();
        FragmentTransaction FragmentTransaction = FragmentManager.beginTransaction(); // העברה למסך אחר בפרגמרנט
        FragmentTransaction.add(R.id.FragmentCon, new FragmentClac()).commit(); //הוספה של הפרגמט למסך המותאם, ADD רק בפעם הראשונה


    }


    public void funcNumber(View view) {

        Button b = (Button) view;
        textView.append(b.getText());

    }

    public void funcOperation(View view) {

        num1 = Integer.parseInt(textView.getText().toString());
        opString = ((Button) view).getText().toString();
        op = ((Button) view).getText().charAt(0);
        textView.setText("");

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.animationbutton); // לחיצה עם סוג כזה של אנימציה של לחצן
        view.startAnimation(animation);

    }

    public void FuncEq(View view) {
        num2 = Integer.parseInt(textView.getText().toString());
        switch (op)
        {
            case '+':
                textView.setText((num1+num2)+"");
                break;
            case '-':
                textView.setText((num1-num2)+"");
                break;
            case '*':
                textView.setText((num1*num2)+"");
                break;
            case '/':
                textView.setText((num1/num2)+"");
                break;
            case 'c':
                textView.setText((num1*num2*0)+"");
                break;
        }
        switch (opString) {
            case "LOG":
                textView.setText(Math.log10(num2) + "");
                break;
            case "TAN":
                textView.setText(Math.tan(num2) + "");
                break;
            case "SIN":
                textView.setText(Math.sin(num2) + "");
                break;
            case "COS":
                textView.setText(Math.cos(num2) + "");
                break;
        }

    }

    public void funcButtonTo2(View view) { // כפתור למסף הבא
        Intent intent= new Intent(this, MainActivity2.class);
        intent.putExtra(KEY,textView.getText().toString());  // העברת מפתח לצד השני
        startActivity(intent);


    }

    private void funcGetFromDB() { // לקריאה ממסד נתונים

        String uid = mAuth.getCurrentUser().getUid();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // מביא את החיבור לfirebase
        DatabaseReference myRef = database.getReference("Person").child(uid); // persons מביאה את הנתיב
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Person person = dataSnapshot.getValue(Person.class);
                TextView t=findViewById(R.id.textViewUser);
                t.setText("welcome " + person.getEmail());

                 Toast.makeText(MainActivity.this,"Email: " + person.getEmail() + " " + "Phone: "+ person.getPhone()+" "+ "Address: "+ person.getAddress() +" "  ,
                                    Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }

        });


    }

    public void loadSecFragment() { //לפנות לפרגמט הבא

        FragmentTransaction FragmentTransaction = FragmentManager.beginTransaction(); // העברה למסך אחר בפרגמרנט
        FragmentTransaction.add(R.id.FragmentSC, new FragmentScClac()).addToBackStack(null).commit(); //הוספה של הפרגמט למסך המותאם, replace לצורך החלפה לא Add
        //addToBackStack - אפשרות לחזרה לאחור



    }


}