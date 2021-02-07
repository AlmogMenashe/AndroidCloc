package com.example.hitfirstapp.activitiys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hitfirstapp.R;

public class MainActivity2 extends AppCompatActivity {

    private final String KEY = "MainActivityKeyName" ;
    String String2 , opString ;
    TextView textView;
    double num1,num2;
    char op ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String result = getIntent().getStringExtra(KEY); // מקבל מפתח מצד שני
        String2 = "welcome to Scientific calculator\n result result =  "; // הודעה בעת מעבר בין סוגי המחשבונים
        // Toast הקפצה של הודעה למסך
        Toast.makeText( this,String2 + result, Toast.LENGTH_LONG).show(); // LENGTH_LONG - 3 sec , show - 1 sec

        textView=findViewById(R.id.textViewResult2);
        textView.setText("");
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
            case "CLEAR":
                textView.setText((num1*num2*0)+""); //יש בעייה לא עובד
                break;

        }
    }

    public void funcButtonTo1(View view) { // כפתור למסף הראשון
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}