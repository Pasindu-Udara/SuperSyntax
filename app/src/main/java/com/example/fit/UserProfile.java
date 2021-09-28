package com.example.fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullName,email,phoneNo,password;
    TextView fullNameLabel, usernameLabel;
    Button deleteBtn , homebtn , bmibtn;


    String _USERNAME, _NAME, _EMAIL, _PHONENO, _PASSWORD;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        fullName=findViewById(R.id.name_profile);
        email=findViewById(R.id.email_profile);
        phoneNo=findViewById(R.id.phone_no_profile);
        password=findViewById(R.id.password_profile);
        fullNameLabel=findViewById(R.id.fullname_field);
        usernameLabel=findViewById(R.id.username_field);
        deleteBtn = findViewById(R.id.deleteBtn);
        homebtn = findViewById(R.id.homemain_btn);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(UserProfile.this,Home.class);
                startActivity(i);
                finish();
            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteItem();
            }
        });

        //showAllData
        showAllData();


    }

    private void showAllData() {

        Intent intent = getIntent();

        _USERNAME = intent.getStringExtra("username");
        _NAME = intent.getStringExtra("name");
        _EMAIL = intent.getStringExtra("email");
        _PHONENO = intent.getStringExtra("phoneNo");
        _PASSWORD = intent.getStringExtra("password");

        fullNameLabel.setText(_NAME);
        usernameLabel.setText(_USERNAME);
        fullName.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
        phoneNo.getEditText().setText(_PHONENO);
        password.getEditText().setText(_PASSWORD);

    }

    public void update(View view){

        if (isNameChanged() || isPasswordChanged() || isPhoneNoChanged() || isEmailChanged()) {
            Toast.makeText(this, "Data has been Updated!", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "Same Data Cannot be Updated!", Toast.LENGTH_LONG).show();

    }

    private boolean isPhoneNoChanged() {
        if (!_PHONENO.equals(phoneNo.getEditText().getText().toString())) {

            reference.child(_USERNAME).child("phoneNo").setValue(phoneNo.getEditText().getText().toString());
            _PHONENO=phoneNo.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isNameChanged() {
        if (!_NAME.equals(fullName.getEditText().getText().toString())) {

            reference.child(_USERNAME).child("name").setValue(fullName.getEditText().getText().toString());
            _NAME=fullName.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isPasswordChanged() {
        if (!_PASSWORD.equals(password.getEditText().getText().toString())) {

            reference.child(_USERNAME).child("password").setValue(password.getEditText().getText().toString());
            _PASSWORD=password.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmailChanged() {
        if (!_EMAIL.equals(email.getEditText().getText().toString())) {

            reference.child(_USERNAME).child("email").setValue(email.getEditText().getText().toString());
            _EMAIL=email.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private void deleteItem(){
        reference.removeValue();
        startActivity(new Intent(UserProfile.this,Login.class));
        Toast.makeText(this, "Profile Deleted..!", Toast.LENGTH_SHORT).show();
    }
}