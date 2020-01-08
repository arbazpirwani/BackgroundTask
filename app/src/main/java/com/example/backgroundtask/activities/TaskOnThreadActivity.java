package com.example.backgroundtask.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.backgroundtask.R;
import com.example.backgroundtask.utils.Constant;
import com.example.backgroundtask.utils.Utility;

public class TaskOnThreadActivity extends BaseActivity {

    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_on_thread);
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

    private void task() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String result = Utility.callAnApi(Constant.BASE_URL);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewResult.setText(result);
                    }
                });
            }
        }).start();
    }
}
