package com.example.backgroundtask.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.backgroundtask.R;
import com.example.backgroundtask.task.CallingIpAddressTask;
import com.example.backgroundtask.utils.Constant;

public class AsyncTaskActivity extends BaseActivity {
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        initialize();
    }

    @Override
    void initialize() {
        initializeView();
        task();
    }

    @Override
    void initializeView() {
        textViewResult = findViewById(R.id.textViewResult);
    }

    @Override
    void setListeners() {
    }

    private void task(){
        new CallingIpAddressTask(textViewResult).execute(Constant.BASE_URL);
    }
}
