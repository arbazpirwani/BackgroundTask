package com.example.backgroundtask.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {


    abstract void initialize();

    abstract void initializeView();

    abstract void setListeners();


    void changeActivity(Class<?> activity) {
        startActivity(new Intent(this, activity));
    }

}
