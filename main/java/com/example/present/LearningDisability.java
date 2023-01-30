package com.example.present;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LearningDisability extends AppCompatActivity {

    ArrayList<LearningDisabilityModel> learingModels;
    int[] learningImages = {R.drawable.learningicon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_disability);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if(currentUser == null){
            Intent intent = new Intent(this, TeacherMain.class);
            startActivity(intent);
            finish();
            return;
        }

        RecyclerView recyclerView = findViewById(R.id.learnRV);
        setLearingModels();

        LearningDisabilityAdapter adapter = new LearningDisabilityAdapter(this, learingModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setLearingModels(){
        String[] learningNames = getResources().getStringArray(R.array.learning_disabilities_text);

        for(int i=0; i< learningNames.length; i++){
            learingModels.add(new LearningDisabilityModel(learningNames[i], learningImages[i]));
        }


    }
}