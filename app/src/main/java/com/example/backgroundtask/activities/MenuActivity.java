package com.example.backgroundtask.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.backgroundtask.R;

public class MenuActivity extends BaseActivity implements View.OnClickListener {

    TextView textViewHeading;
    Button buttonResultThreadTask;
    Button buttonResultAsyncTask;
    Button buttonResultAsyncTaskLoader;
    Button buttonPowerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initialize();
    }


    @Override
    void initialize() {
        initializeView();
        setListeners();
    }

    @Override
    void initializeView() {
        textViewHeading = findViewById(R.id.textViewHeading);
        buttonResultThreadTask = findViewById(R.id.buttonResultThreadTask);
        buttonResultAsyncTask = findViewById(R.id.buttonResultAsyncTask);
        buttonResultAsyncTaskLoader = findViewById(R.id.buttonResultAsyncTaskLoader);
        buttonPowerReceiver = findViewById(R.id.buttonPowerReceiver);
    }

    @Override
    void setListeners() {
        textViewHeading.setOnClickListener(this);
        buttonResultThreadTask.setOnClickListener(this);
        buttonResultAsyncTask.setOnClickListener(this);
        buttonResultAsyncTaskLoader.setOnClickListener(this);
        buttonPowerReceiver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonResultThreadTask:
                changeActivity(TaskOnThreadActivity.class);
                break;
            case R.id.buttonResultAsyncTask:
                changeActivity(AsyncTaskActivity.class);
                break;
            case R.id.buttonResultAsyncTaskLoader:
                changeActivity(AsyncLoaderActivity.class);
                break;
            case R.id.buttonPowerReceiver:
                changeActivity(PowerReceiverActivity.class);
                break;
        }
    }

}
