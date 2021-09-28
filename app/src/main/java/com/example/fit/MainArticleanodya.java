package com.example.fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainArticleanodya extends AppCompatActivity {

    private Button viewbutton2;
    private FloatingActionButton addbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainarticleanodya);

        viewbutton2 = (Button) findViewById(R.id.viewbutton2);
        viewbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainArticleanodya.this, ViewArticleanodya.class);
                startActivity(intent);
            }
        });


        addbutton = findViewById(R.id.addbutton);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainArticleanodya.this, AddArticlesanodya.class);
                startActivity(intent);
            }

        });
}
}