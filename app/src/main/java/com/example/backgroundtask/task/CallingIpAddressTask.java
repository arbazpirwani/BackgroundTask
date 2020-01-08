package com.example.backgroundtask.task;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.backgroundtask.utils.Utility;

import java.lang.ref.WeakReference;

public class CallingIpAddressTask extends AsyncTask<String, Integer, String> {

    private WeakReference<TextView> textViewResult;


    public CallingIpAddressTask(TextView tv) {

        textViewResult = new WeakReference<>(tv);

    }

    @Override
    protected String doInBackground(String... strings) {

        int i = 0;
        while (i <= 50) {
            try {
                Thread.sleep(50);
                publishProgress(i);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Utility.callAnApi(strings[0]);
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        textViewResult.get().setText(String.format("%s%%", values[0].toString()));
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textViewResult.get().setText(s);
    }
}
