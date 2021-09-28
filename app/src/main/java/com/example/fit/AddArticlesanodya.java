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

public class AddArticlesanodya extends AppCompatActivity {

    private Button button11;
    private Button Savebutton;

    private EditText Category;
    private EditText Articletitle;
    private EditText Typearticle;
    private FirebaseFirestore db;
    private String uId, uCategory,uArticletitle,uTypearticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_articlesanodya);


        button11 = (Button) findViewById(R.id.button11);
        Savebutton = findViewById(R.id.Savebutton);

        Category = findViewById(R.id.Category);
        Articletitle = findViewById(R.id.Typeproblem);
        Typearticle = findViewById(R.id.Typearticle);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Savebutton.setText("Update");
            uId = bundle.getString("uId");
            uCategory = bundle.getString("uCategory");
            uArticletitle = bundle.getString("uArticletitle");
            uTypearticle = bundle.getString("uTypearticle");
            Category.setText(uCategory);
            Articletitle.setText(uArticletitle);
            Typearticle.setText(uTypearticle);

        }else{
            Savebutton.setText("Save");
        }


        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddArticlesanodya.this, ReviewArticleanodya.class);
                startActivity(intent);
            }
        });


        Savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String category = Category.getText().toString();
                String articletitle = Articletitle.getText().toString();
                String typearticle = Typearticle.getText().toString();

                Bundle bundle1 = getIntent().getExtras();
                if(bundle1 != null){
                    String id = uId;
                    updateToFireStore(id, category, articletitle, typearticle);
                }else{
                    String id = UUID.randomUUID().toString();
                    saveToFireStore(id, category, articletitle, typearticle);
                }


            }
        });
    }
    private void updateToFireStore(String id, String category, String articletitle, String typearticle){
        db.collection("Articles").document(id).update("category",category,"articletitle",articletitle, "typearticle",typearticle)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AddArticlesanodya.this, "Data updated", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(AddArticlesanodya.this,"Error : " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddArticlesanodya.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToFireStore(String id, String category, String articletitle, String typearticle) {
        if (!category.isEmpty() && !articletitle.isEmpty() && !typearticle.isEmpty()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("category", category);
            map.put("articletitle", articletitle);
            map.put("typearticle", typearticle);

            db.collection("Articles").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AddArticlesanodya.this, "data saved", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddArticlesanodya.this, "failed", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            Toast.makeText(this, " empty fields", Toast.LENGTH_SHORT).show();
        }


    }
}
