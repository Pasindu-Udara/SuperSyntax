package com.example.fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button workoutbtn, dietbtn, articlebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        workoutbtn=findViewById(R.id.homebtn1);
        dietbtn=findViewById(R.id.homebtn2);
        articlebtn=findViewById(R.id.homebtn3);

        workoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(Home.this,MainActivity6pasi.class);
                startActivity(i);

            }
        });

        dietbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(Home.this,MainActivity.class);
                startActivity(i);

            }
        });
        articlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(Home.this,MainArticleanodya.class);
                startActivity(i);

            }
        });

    }
}