package com.example.backgroundtask.loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.backgroundtask.utils.NetworkUtils;

public class FetchBookLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    public FetchBookLoader(@NonNull Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }


    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
