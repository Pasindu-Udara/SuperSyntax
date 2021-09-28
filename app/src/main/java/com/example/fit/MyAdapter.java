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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ShowActivity activity;
    private List<Model> mList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();



    public MyAdapter(ShowActivity activity, List<Model> mList) {
        this.activity = activity;
        this.mList = mList;
    }

    public void updateData(int position){
        Model item = mList.get(position);
        Bundle bundle =new Bundle();
        bundle.putString("uId" , item.getId());
        bundle.putString("uName" , item.getName());
        bundle.putString("uBreakfast" , item.getBreakfast());
        bundle.putString("uLunch" , item.getLunch());
        bundle.putString("uTea" , item.getTea());
        bundle.putString("uDinner" , item.getDinner());
        Intent intent = new Intent(activity, Form.class);
        intent.putExtras(bundle);
        activity.startActivity(intent); //the data will be passed to the form


    }

    public void deleteData(int position){
        Model item = mList.get(position);
        db.collection("Documents").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyItemRemoved(position);
                            Toast.makeText(activity, "Data Deleted..!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activity, "Error : "+task.getException()
                                    .getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void notifyRemoved(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.notes, parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(mList.get(position).getName());
        holder.breakfast.setText(mList.get(position).getBreakfast());
        holder.lunch.setText(mList.get(position).getLunch());
        holder.tea.setText(mList.get(position).getTea());
        holder.dinner.setText(mList.get(position).getDinner());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView id, name, breakfast, lunch, tea, dinner;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.title_text);
            breakfast = itemView.findViewById(R.id.breakfast_text);
            lunch = itemView.findViewById(R.id.lunch_text);
            tea = itemView.findViewById(R.id.tea_text);
            dinner = itemView.findViewById(R.id.dinner_text);

        }
    }
}
