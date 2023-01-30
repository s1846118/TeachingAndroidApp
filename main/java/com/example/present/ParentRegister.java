package com.example.present;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ParentRegister extends AppCompatActivity {

    private FirebaseAuth newAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_register);

        newAuth = FirebaseAuth.getInstance();
        if(newAuth.getCurrentUser() != null){
            finish();
            return;
        }

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerParent();
            }
        });

        TextView switchToLogin = findViewById(R.id.switchToLogin);
        switchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToLoginPage();
            }
        });


    }

    private void registerParent() {
        EditText edtxtFirstName =findViewById(R.id.edtxtFirstName);
        EditText edtxtLastName =findViewById(R.id.edtxtLastName);
        EditText edtxtEmail =findViewById(R.id.edtxtEmail);
        EditText edtxtPassword =findViewById(R.id.edtxtPassword);
        EditText edtxtChildID =findViewById(R.id.edtxtChildID);


        String parentFirstName = edtxtFirstName.getText().toString();
        String parentLastName = edtxtLastName.getText().toString();
        String parentEmail = edtxtEmail.getText().toString();
        String parentPassword = edtxtPassword.getText().toString();
        String childID = edtxtChildID.getText().toString();

        if(parentFirstName.isEmpty() || parentLastName.isEmpty() || parentEmail.isEmpty() || parentPassword.isEmpty() || childID.isEmpty()){
            Toast.makeText(this, "Please enter value for all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        newAuth.createUserWithEmailAndPassword(parentEmail, parentPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Parent parent = new Parent(parentFirstName, parentLastName, parentEmail, childID);

                    FirebaseDatabase.getInstance().getReference("Parents").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(parent).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    showMainPage();
                                }
                            });
                }else {
                    Toast.makeText(ParentRegister.this, "Authentication Failed. Please Try Again", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void showMainPage(){
        Intent intent = new Intent(this, ParentMain.class);
        startActivity(intent);
        finish();
    }

    private void switchToLoginPage() {
        Intent intent = new Intent(this, ParentLogin.class);
        startActivity(intent);
        finish();
    }
}