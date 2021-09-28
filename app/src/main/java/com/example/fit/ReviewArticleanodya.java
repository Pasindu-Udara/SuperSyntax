package com.example.fit;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ReviewArticleanodya extends AppCompatActivity {

    private RecyclerView recyclerview;
    private FirebaseFirestore db;
    private Myadapteranodya adapter;
    private List<Modelanodya> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_articleanodya);

        recyclerview = findViewById(R.id.Recyclerview2);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        adapter = new Myadapteranodya(this, list);
        recyclerview.setAdapter(adapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHelperanodya(adapter));
        touchHelper.attachToRecyclerView(recyclerview);
        showData();

    }

    public void showData() {
        db.collection("Articles").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for (DocumentSnapshot snapshot : task.getResult()) {
                            Modelanodya model = new Modelanodya(snapshot.getString("id"), snapshot.getString("category"), snapshot.getString("articletitle"), snapshot.getString("typearticle"));
                            list.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ReviewArticleanodya.this, "oopsa..something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}










