package com.example.present;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

public class SelectDate extends AppCompatActivity {

    EditText dateET;
    ImageButton calImage;
    Button nextBtn;
    String attDate;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);

        dateET = findViewById(R.id.dateET);
        calImage = findViewById(R.id.calImage);
        nextBtn = findViewById(R.id.nextBtn);

        calImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker dp = new DatePicker(SelectDate.this);
                int attDay = dp.getDayOfMonth();
                int attMonth = dp.getMonth();
                int attYear = dp.getYear();

                datePickerDialog = new DatePickerDialog(SelectDate.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(day+"-");
                        stringBuilder.append((month+1)+"-");
                        stringBuilder.append(year);
                        dateET.setText(stringBuilder.toString());
                    }

                }, attDay, attMonth, attDay);
                datePickerDialog.show();

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attDate = dateET.getText().toString();
                Intent intent=new Intent(SelectDate.this, TakeAttendance.class);
                intent.putExtra("DATE", attDate);
                startActivity(intent);
            }
        });







    }
}