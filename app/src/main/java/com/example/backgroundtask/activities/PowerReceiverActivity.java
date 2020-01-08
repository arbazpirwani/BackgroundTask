package com.example.backgroundtask.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.backgroundtask.R;
import com.example.backgroundtask.receivers.PowerConnectionReceiver;

public class PowerReceiverActivity extends BaseActivity {

    private PowerConnectionReceiver powerConnectionReceiver = new PowerConnectionReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_task);
        initialize();
    }


    @Override
    void initialize() {

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        this.registerReceiver(powerConnectionReceiver, filter);
    }

    @Override
    void initializeView() {

    }

    @Override
    void setListeners() {

    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(powerConnectionReceiver);
        super.onDestroy();
    }
}
