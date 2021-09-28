package com.example.fit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class AskMyProblemsanodya extends AppCompatActivity {

    private Button button15;
    private Button Savebutton2;

    private EditText Typeproblem;
    private EditText Problem;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_my_problemsanodya);


        Savebutton2 = findViewById(R.id.Savebutton2);
        Typeproblem = findViewById(R.id.Typeproblem);
        Problem = findViewById(R.id.Problem);
        button15 = (Button) findViewById(R.id.button15);

        db = FirebaseFirestore.getInstance();

        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskMyProblemsanodya.this, ReviewMyProblemanodya.class);
                startActivity(intent);
            }
        });


        Savebutton2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String typeproblem = Typeproblem.getText().toString();
            String problem = Problem.getText().toString();
            String id = UUID.randomUUID().toString();
            saveToFireStore(id, typeproblem, problem);


        }
    });
}
    private void saveToFireStore(String id, String typeproblem, String problem) {
        if (!typeproblem.isEmpty() && !problem.isEmpty()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("typeproblem", typeproblem);
            map.put("problem", problem);


            db.collection("FAQ").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AskMyProblemsanodya.this, "data saved", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AskMyProblemsanodya.this, "failed", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            Toast.makeText(this, " empty fields", Toast.LENGTH_SHORT).show();
        }
    }
}