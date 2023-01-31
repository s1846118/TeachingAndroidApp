package com.example.present;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.present.StudentAttendance;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class attAdapter extends RecyclerView.Adapter<attVH> {

    // List of recycler view items - we have list of students and the present or absent buttons
    public List<StudentAttendance> items;

    // Adapter to add remove, edit recycler view items!
    public attAdapter(List<StudentAttendance> items){
        this.items = items;
    }

    @NonNull
    @Override
    public attVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        return new attVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull attVH holder, int position) {
        holder.textview.setText(items.get(position).getFirstName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class attVH extends RecyclerView.ViewHolder{

    TextView textview;
    private attAdapter adapter;

    public attVH(@NonNull View itemView){
        super(itemView);

        textview = itemView.findViewById(R.id.studentName);

        // This is an event listener in the event user clicks present!
        itemView.findViewById(R.id.Present).setOnClickListener(view -> {
            //Here we must write the logic for marking the student present!
            Log.d("Present", textview.getText().toString());
            adapter.items.get(getBindingAdapterPosition()).setPresent(true);
            adapter.items.get(getBindingAdapterPosition()).setAbsent(false);

            Button present = (Button) itemView.findViewById(R.id.Present);
            Button absent = (Button) itemView.findViewById((R.id.Absent));
            present.setBackgroundColor(Color.BLUE);
            absent.setBackgroundColor(Color.GRAY);
            adapter.notifyItemChanged(getBindingAdapterPosition());
        });

        // This is the event listener in the event user clicks absent!
        itemView.findViewById(R.id.Absent).setOnClickListener(view -> {
            //Here we must write the logic for marking the student absent!
            Log.d("Absent", textview.getText().toString());
            adapter.items.get(getBindingAdapterPosition()).setAbsent(true);
            adapter.items.get(getBindingAdapterPosition()).setPresent(false);
            adapter.notifyItemChanged(getBindingAdapterPosition());

            Button present = (Button) itemView.findViewById(R.id.Present);
            Button absent = (Button) itemView.findViewById((R.id.Absent));

            absent.setBackgroundColor(Color.BLUE);
            present.setBackgroundColor(Color.GRAY);
            // How to remove a student from the list, might be a good idea once functionality has  been written to write to DB
//            adapter.items.remove(getBindingAdapterPosition());
//            adapter.notifyItemRemoved(getBindingAdapterPosition());

        });
    }

    public attVH linkAdapter(attAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
