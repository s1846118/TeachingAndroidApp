package com.example.present;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class NoteTaking extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String mUserID;
    private DatabaseReference reference;


    private RecyclerView taskRV;
    private FloatingActionButton floatingActionButton;

    private ProgressDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_taking);

        //initialise fb
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLogin.class);
            startActivity(intent);
            finish();
            return;
        }


        mUser = mAuth.getCurrentUser();
        mUserID = mUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("Notes").child(mUserID);

        loader = new ProgressDialog(this);

        taskRV = findViewById(R.id.taskRV);
        floatingActionButton = findViewById(R.id.fab);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        taskRV.setHasFixedSize(true);
        taskRV.setLayoutManager(linearLayoutManager);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });


    }



    private void addTask(){
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View myView = inflater.inflate(R.layout.add_task, null);
        myDialog.setView(myView);

        AlertDialog dialog = myDialog.create();
        dialog.setCancelable(false);

        final EditText taskET = findViewById(R.id.taskET);
        final EditText descET = findViewById(R.id.descET);
        Button saveBtn = findViewById(R.id.saveBtn);
        Button cancelBtn = findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTask = taskET.getText().toString();
                String newDEsc = descET.getText().toString();
                String id = reference.push().getKey();
                String newDate = DateFormat.getDateInstance().format(new Date());


                if(newTask.isEmpty()){
                    taskET.setError("Please Enter a Task");
                }else if(newDEsc.isEmpty()){
                    descET.setError("Please Enter a Task");
                }else {
                    loader.setMessage("Task Being Added");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    Notes note = new Notes(newTask, newDate, id, newDate);
                    reference.child(id).setValue(note).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(NoteTaking.this, "Task Added Successfully", Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            } else {
                                String error = task.getException().toString();
                                Toast.makeText(NoteTaking.this, "Failed, Please Try Again", Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });
                }

            }
        });

       // dialog.show();

    }
}