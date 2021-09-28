package com.example.fit;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Myadapteranodya extends RecyclerView.Adapter<Myadapteranodya.MyViewHolderanodya> {

    private ReviewArticleanodya activity;
    private List<Modelanodya> mList ;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Myadapteranodya(ReviewArticleanodya activity, List<Modelanodya> mList){
        this.activity = activity;
        this.mList = mList;
    }
    public void updateData(int position){
        Modelanodya item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId",item.getId());
        bundle.putString("uCategory",item.getCategory());
        bundle.putString("uArticletitle",item.getArticletitle());
        bundle.putString("uTypearticle",item.getTypearticle());
        Intent intent = new Intent(activity, AddArticlesanodya.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
    public void deleteData(int position){
        Modelanodya item = mList.get(position);
        db.collection("Articles").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity, "Data deleted",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activity,"Error : "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void notifyRemoved(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }

    @NonNull
    @Override
    public MyViewHolderanodya onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.itemanodya, parent, false);
        return new MyViewHolderanodya(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderanodya holder, int position) {
        holder.category.setText(mList.get(position).getCategory());
        holder.articletitle.setText(mList.get(position).getArticletitle());
        holder.typearticle.setText(mList.get(position).getTypearticle());


    }

    @Override
    public int getItemCount() {

        return mList.size();
    }

    public static class MyViewHolderanodya extends  RecyclerView.ViewHolder{
        TextView category, articletitle, typearticle;

        public MyViewHolderanodya(@NonNull View itemView){
            super(itemView);

            category = itemView.findViewById(R.id.Category);
            articletitle = itemView.findViewById(R.id.Typeproblem);
            typearticle = itemView.findViewById(R.id.Typearticle);

        }
    }

}

