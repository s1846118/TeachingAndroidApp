package com.example.present;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitialLogin extends AppCompatActivity {

    Button teacherButton;
    Button parentButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_login);

        teacherButton = findViewById(R.id.teacherBtn);
        parentButton = findViewById(R.id.parentBtn);

        teacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teacherLogin();
            }
        });
        parentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentLogin();
            }
        });
    }

    private void teacherLogin() {
        Intent intent = new Intent(this, TeacherLogin.class);
        startActivity(intent);
    }

    private void parentLogin() {
        Intent intent = new Intent(this, ParentLogin.class);
        startActivity(intent);
    }


}