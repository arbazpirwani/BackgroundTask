package com.example.backgroundtask.utils;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG =
            NetworkUtils.class.getSimpleName();

    // Base URL for Books API.
    private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    // Parameter for the search string.
    private static final String QUERY_PARAM = "q";
    // Parameter that limits search results.
    private static final String MAX_RESULTS = "maxResults";
    // Parameter to filter by print type.
    private static final String PRINT_TYPE = "printType";

    public static String getBookInfo(String queryString) {

        HttpURLConnection httpURLConnection = null;

        BufferedReader bufferedReader = null;

        String bookJson;

        try {
            Uri builtUri = Uri.parse(BOOK_BASE_URL)
                    .buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();


            //builtUri.toString() output = https://www.googleapis.com/books/v1/volumes?q=kotlin&maxResults=10&printType=books

            URL requestURL = new URL(builtUri.toString());

            httpURLConnection = (HttpURLConnection) requestURL.openConnection();

            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.connect();


            bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            httpURLConnection.getInputStream()
                    )
            );


            StringBuilder stringBuilder = new StringBuilder();

            String lineInput;
            while ((lineInput = bufferedReader.readLine()) != null) {
                stringBuilder.append(lineInput);
                stringBuilder.append("\n");
            }

            bufferedReader.close();

            if (stringBuilder.length() == 0) {
                return null;
            }

            bookJson = stringBuilder.toString();


        } catch (Exception e) {
            return e.getClass().getSimpleName();
        } finally {

            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, bookJson);

        return bookJson;
    }


}
