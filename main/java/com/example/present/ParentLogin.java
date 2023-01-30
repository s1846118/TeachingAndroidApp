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

public class ParentLogin extends AppCompatActivity {
    private FirebaseAuth newAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);

        newAuth = FirebaseAuth.getInstance();
        //if user exists or not
        if (newAuth.getCurrentUser() != null){
            finish();
            return;
        }

        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateParent();
            }
        });

        TextView registerTV = findViewById(R.id.registerTV);
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerTV();
            }
        });

    }

    private void authenticateParent() {
        EditText emailEdTxt = findViewById(R.id.emailEdTxt);
        EditText passwordEdTxt = findViewById(R.id.passwordEdTxt);

        String email = emailEdTxt.getText().toString();
        String password = passwordEdTxt.getText().toString();

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        newAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showMainPage();
                        } else {
                            Toast.makeText(ParentLogin.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void registerTV() {
        Intent intent = new Intent(this, ParentRegister.class);
        startActivity(intent);
        finish();
    }

    private void showMainPage(){
        Intent intent = new Intent(this, ParentMain.class);
        startActivity(intent);
        finish();
    }
}