package com.example.present;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.Intent;
import com.example.present.StudentAttendance;
public class TakeAttendance extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Grab date data from prior intent
        Intent intent = getIntent();
        String date = intent.getStringExtra("DATE");

        Log.d("DATE", date);

        // Initialise layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendence_work);

        // Grab students and information from Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Students").child(currentUser.getUid());
        DatabaseReference ref2 = database.getReference("Attendence").child(currentUser.getUid());

        // Initialise date entry to the database... We will then add the student in the adapter!
        ref2.child(currentUser.getUid()).setValue(date);

        // Right now we just have a String in out items... We could make this different to add data
        List<StudentAttendance> items = new LinkedList<>();

        // Initialise adapter and layout manager and stuff I currently do not understand!!
        RecyclerView recyclerView = findViewById(R.id.recyclerViewStudentAttendance);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        attAdapter adapter = new attAdapter(items);
        recyclerView.setAdapter(adapter);

        // Submission button TODO- Change to submit to firebase currently just adds to view
        findViewById(R.id.Submittion_button).setOnClickListener(view -> {
            for (int i = 0; i < items.size(); i++){
                Log.d("Names", items.get(i).getFirstName());
                Log.d("There", items.get(i).getPresent().toString());
                // Now here I need to check if each item has absent or present to add to firebase!

            }
        });

        // Grab relevant data and add to recyclerView
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Loops through students!
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // Grab only first name
                    String name = dataSnapshot.child("firstName").getValue().toString();
                    String second_name = dataSnapshot.child("surName").getValue().toString();
                    String dob = dataSnapshot.child("dateofBirth").getValue().toString();
                    String stuNo = dataSnapshot.child("studentNo").getValue().toString();
                    items.add(new StudentAttendance(name, second_name, dob, stuNo, false, false));
                }
                // Notify adapter of changes made!
                adapter.notifyItemInserted(items.size()-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}


//    // ListView myListview;
//    RecyclerView recyclerView;
//    ListAdapter adapter;
//    ArrayList<Student> studentLists;
//    FirebaseAuth auth = FirebaseAuth.getInstance();
//    FirebaseUser currentUser = auth.getCurrentUser();
//
//    //DatabaseReference studentDBRef;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.attendence_work);
//
//        recyclerView = findViewById(R.id.recyclerViewStudentAttendance);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference reference = database.getReference("Students").child(currentUser.getUid());
//
//        // Need to make new adapter!
//        studentLists = new ArrayList<>();
//        adapter = new ListAdapter(this, studentLists);
//        recyclerView.setAdapter(adapter);
//
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Log.d("WHAT", dataSnapshot.getValue().toString());
//                    Student student = dataSnapshot.getValue(Student.class);
//                    //System.out.println("Line 56" + task.toString());
//                    studentLists.add(student);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }