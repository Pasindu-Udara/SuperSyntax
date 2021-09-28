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

public class MyAdapterpasi extends RecyclerView.Adapter <MyAdapterpasi.MyViewHolderpasi>{

    private ShowActivitypasi activity;
    private List<Modelpasi> mList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MyAdapterpasi(ShowActivitypasi activity, List<Modelpasi> mList){
        this.activity = activity;
        this.mList = mList;
    }

    public void updateData(int position){
        Modelpasi item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId" , item.getId());
        bundle.putString("uTitle" , item.getTitle());
        bundle.putString("uDesc" , item.getDesc());
        Intent intent = new Intent(activity , MainActivitypasi.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

public void deleteData(int position){
        Modelpasi item= mList.get(position);
        db.collection("Workouts").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    notifyRemoved(position);
                    Toast.makeText(activity,"Data deleted !!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(activity,"Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
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
    public MyViewHolderpasi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.itempasi, parent , false);
        return new MyViewHolderpasi(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderpasi holder, int position) {

        holder.title.setText(mList.get(position).getTitle());
        holder.desc.setText(mList.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return mList.size();


    }

    public static class MyViewHolderpasi extends RecyclerView.ViewHolder{

        TextView title , desc;

        public MyViewHolderpasi(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_text);
            desc = itemView.findViewById(R.id.desc_text);
        }
    }

}
