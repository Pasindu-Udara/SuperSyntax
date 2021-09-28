package com.example.fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_veg1, btn_military, btn_other;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_veg1 = (Button) findViewById(R.id.btn_veg1);
        btn_military = (Button) findViewById(R.id.btn_military);
        btn_other = (Button) findViewById(R.id.btn_other);


        btn_veg1.setOnClickListener(this);
        btn_military.setOnClickListener(this);
        btn_other.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
           switch(v.getId()){
                case R.id.btn_veg1:
                    Intent intent6 = new Intent(this, Vegetarian.class);
                    startActivity(intent6);
                    break;
                case R.id.btn_military:
                    Intent intent2 = new Intent(this, Military_Page.class);
                    startActivity(intent2);
                    break;
                case R.id.btn_other:
                    Intent intent3 = new Intent(this, Personalized_Page.class);
                    startActivity(intent3);
                    break;

            }
    }
}