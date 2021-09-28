package com.example.fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyPlans extends AppCompatActivity {

    Button btn_createNew;
    Button btn_created;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plans);

        btn_createNew = findViewById(R.id.btn_createNew);
        btn_created = findViewById(R.id.btn_created);

        btn_created.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyPlans.this , ShowActivity.class));
            }
        });

        btn_createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MyPlans.this, Form.class);
                startActivity(intent4);
            }
        });

    }
}