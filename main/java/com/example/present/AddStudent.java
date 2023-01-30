package com.example.present;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudent extends AppCompatActivity {

    //add class name
    EditText firstNameET;
    EditText lastNameET;
    EditText dobET;
    EditText studentIDET;
    EditText parent1NameET;
    EditText parent2NameET;
    EditText parent1NoET;
    EditText parent2NoET;
    Button addNewStudButton;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String mUserID;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //FirebaseUser userID = userID.getUid();
        //

       // if(mAuth.getCurrentUser() != null){
       //     finish();
      //      return;
      //  }
        if(currentUser == null){
            Intent intent = new Intent(this, TeacherMain.class);
            startActivity(intent);
            finish();
            return;
        }

        addNewStudButton = findViewById(R.id.addNewStudButton);
        addNewStudButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAStudent();
            }
        }
        );
    }

    private void addAStudent() {

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mUserID = mUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("Students").child(mUserID);


        //initialise values
        firstNameET = findViewById(R.id.firstNameET);
        lastNameET = findViewById(R.id.lastNameET);
        dobET = findViewById(R.id.dobET);
        studentIDET = findViewById(R.id.studentIDET);
        parent1NameET = findViewById(R.id.parent1NameET);
        parent2NameET = findViewById(R.id.parent2NameET);
        parent1NoET = findViewById(R.id.parent1NoET);
        parent2NoET = findViewById(R.id.parent2NoET);

        //convert values to String
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String dateOfBirth =  dobET.getText().toString();
        //String studentID = studentIDET.getText().toString(); //will be same id!!
        String parent1Name = parent1NameET.getText().toString();
        String parent2Name = parent2NameET.getText().toString();
        String parent1No = parent1NoET.getText().toString();
        String parent2No = parent2NoET.getText().toString();
        String id = reference.push().getKey();

        //if any fields are empty - except parent 2 details (as they may not be available)
        if(firstName.isEmpty() || lastName.isEmpty() || dateOfBirth.isEmpty() || parent1Name.isEmpty() || parent1No.isEmpty()) {
            Toast.makeText(this, "Please enter value for all fields", Toast.LENGTH_SHORT).show();
            return;
        } else {                                                                      //("Students")
          //  FirebaseDatabase.getInstance().getReference().child("users").push().child("Student Name").setValue(firstName + lastName);
          //  FirebaseDatabase.getInstance().getReference().child("users").push().child("Date of Birth").setValue(dateOfBirth);
            Student student = new Student(firstName, lastName, dateOfBirth, id);
          //  FirebaseDatabase.getInstance().getReference().child("users").push().child("Students").setValue(student);
           // this one: FirebaseDatabase.getInstance().getReference().push().child("Students").setValue(student);
             // FirebaseDatabase.getInstance().getReference().child("users").push().child("Students").setValue(student);
            //String id = reference.push().getKey();
            reference.child(id).setValue(student);


                                                                          //FirebaseDatabase.getInstance().getReference().child("users").getCurrentUser().push().child("Students").setValue(student);
            //FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            Toast.makeText(AddStudent.this, "New Student Has Been Added", Toast.LENGTH_SHORT).show();
        }

        //use hashmap for creating students




    }
}