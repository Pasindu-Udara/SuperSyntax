package com.example.fit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class Form extends AppCompatActivity {

    private EditText title, breakfast, lunch, tea, dinner;
    private Button btnsavePlan;
    private FirebaseFirestore db;

    private String uName, uBreakfast, uLunch, uTea, uDinner, uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        title = findViewById(R.id.et_title);
        breakfast = findViewById(R.id.et_breakfast);
        lunch = findViewById(R.id.et_lunch);
        tea = findViewById(R.id.et_tea);
        dinner = findViewById(R.id.et_dinner);

        btnsavePlan = findViewById(R.id.btn_savePlan);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            btnsavePlan.setText("Update");
            uName = bundle.getString("uName");
            uBreakfast = bundle.getString("uBreakfast");
            uLunch = bundle.getString("uLunch");
            uTea = bundle.getString("uTea");
            uDinner = bundle.getString("uDinner");
            uId = bundle.getString("uId");

            title.setText(uName);
            breakfast.setText(uBreakfast);
            lunch.setText(uLunch);
            tea.setText(uTea);
            dinner.setText(uDinner);

        }else{
            btnsavePlan.setText("Save");
        }


        btnsavePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = title.getText().toString();
                String Breakfast = breakfast.getText().toString();
                String Lunch = lunch.getText().toString();
                String Tea = tea.getText().toString();
                String Dinner = dinner.getText().toString();

                Bundle bundle1 = getIntent().getExtras();
                if(bundle1 != null){

                    String id = uId;
                    updateToFireStore(id , Name, Breakfast, Lunch, Tea, Dinner);

                }else{
                    String id = UUID.randomUUID().toString();

                    saveToFireStore(id , Name , Breakfast , Lunch , Tea , Dinner);
                }


            }
        });

    }

    private void updateToFireStore(String id, String Name, String Breakfast, String Lunch, String Tea, String Dinner){
        db.collection("Documents").document(id)
                .update("Name", Name, "Breakfast", Breakfast, "Lunch", Lunch, "Tea", Tea, "Dinner", Dinner)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Form.this, "Data updated..!!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Form.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Form.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToFireStore(String id, String Name, String Breakfast, String Lunch, String Tea, String Dinner){

        if(!Name.isEmpty()){
            HashMap<String , Object> map = new HashMap<>();
            map.put("id" , id);
            map.put("Name" , Name);
            map.put("Breakfast" , Breakfast);
            map.put("Lunch" , Lunch);
            map.put("Tea" , Tea);
            map.put("Dinner" , Dinner);

            db.collection("Documents").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Form.this, "Data saved..!", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Form.this, "Failed to insert..!", Toast.LENGTH_SHORT).show();
                }
            });

        }else
            Toast.makeText(this, "Your plan should have a name", Toast.LENGTH_SHORT).show();
    }

}