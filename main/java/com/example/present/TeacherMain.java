package com.example.present;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherMain extends AppCompatActivity {

    TextView tvFirstName;
    TextView tvLastName;
    TextView tvEmail;
    Button logout;
    Button btnAddStu;
    Button btnViewStu;
    Button btnAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialise fb
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLogin.class);
            startActivity(intent);
            finish();
            return;
        }

        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvEmail = findViewById(R.id.tvEmail);

        btnAddStu = findViewById(R.id.btnAddStu);
        btnAddStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { addNewStudent(); }
        });

        btnViewStu = findViewById(R.id.btnViewStu);
        btnViewStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewStudents();
            }
        });

        btnAttendance = findViewById(R.id.btnAttendance);
        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeAttendance();
            }
        });

        logout = findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user != null) /* && if student.className = user.className*/{
                    tvFirstName.setText("First Name: " + user.firstName);
                    tvLastName.setText("Last Name: " + user.lastName);
                    tvEmail.setText("Email: " + user.email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void viewStudents() {
        Intent intent = new Intent(TeacherMain.this, ViewStudents.class);
        startActivity(intent);
    }

    private void addNewStudent() {
        Intent intent = new Intent(TeacherMain.this, AddStudent.class);
        startActivity(intent);
    }

    private void takeAttendance(){
        Intent intent = new Intent(TeacherMain.this, SelectDate.class);
        startActivity(intent);
    }

    private void logoutUser(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, InitialLogin.class);
        startActivity(intent);
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //view task page
        if (id == R.id.learningItem) {
            Intent intent = new Intent(this, LearningDisability.class);
            startActivity(intent);
           // finish();
        }

        if (id == R.id.todoItem) {
            Intent intent = new Intent(this, NoteTaking.class);
            startActivity(intent);
            // finish();
        }
        return true;
    }
}