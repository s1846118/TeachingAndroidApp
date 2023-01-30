package com.example.present;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

   // private Activity mContext;
    //List<Student> studentList;

    Context context;
    ArrayList<Student> studentList;
    //ArrayList<Student> presentList;


    public ListAdapter(android.content.Context context, ArrayList<Student> studentList){ //ArrayList<Student> presentList
        //super(context, R.layout.list_students, studentList);
        this.context= context;
        this.studentList = studentList;
        //this.presentList = presentList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_students, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.firstNameTxt.setText(student.getFirstName());
        holder.lastNameTxt.setText(student.getSurName());
        holder.idTxt.setText(student.getStudentNo());
        holder.dobTxt.setText(student.getDateofBirth());
    }

    @Override
    public int getItemCount(){
        return studentList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView firstNameTxt;
        TextView lastNameTxt;
        TextView idTxt;
        TextView dobTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firstNameTxt = itemView.findViewById(R.id.tvForeName);
            lastNameTxt = itemView.findViewById(R.id.tvLastName);
            idTxt = itemView.findViewById(R.id.tvStudentNo);
            dobTxt = itemView.findViewById(R.id.tvDOB);
        }

    }

}

