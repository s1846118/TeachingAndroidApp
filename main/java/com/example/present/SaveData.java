package com.example.present;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.present.User;

public class SaveData {

    public String teacher_ShiftLoadData(Context context) {
        SharedPreferences pref =context.getSharedPreferences("shift", MODE_PRIVATE);
        String  values = pref.getString("isUserLogIn",null);
        return values;


    }

    public String teacher_DeptLoadData(Context context) {
        SharedPreferences pref =context.getSharedPreferences("tdept", MODE_PRIVATE);
        String  values = pref.getString("isUserLogIn",null);
        return values;


    }

}
