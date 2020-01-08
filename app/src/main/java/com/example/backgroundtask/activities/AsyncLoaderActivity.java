package com.example.backgroundtask.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.backgroundtask.R;
import com.example.backgroundtask.loaders.FetchBookLoader;

import org.json.JSONArray;
import org.json.JSONObject;

public class AsyncLoaderActivity extends BaseActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {
    TextView textViewLabel;
    EditText editTextBookInput;
    Button buttonSearchBook;
    TextView textViewBookTitle;
    TextView textViewBookAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_loader);
        initialize();

    }

    public void searchBooks(View v) {

        String query = editTextBookInput.getText().toString();


        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo networkInfo = null;

        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()
                && query.length() != 0) {

            Bundle queryBundle = new Bundle();

            queryBundle.putString("query", query);

            getSupportLoaderManager().restartLoader(0, queryBundle, this);
            textViewBookAuthor.setText("");
            textViewBookTitle.setText("Loading...");
        } else {
            if (query.length() == 0) {
                textViewBookAuthor.setText("");
                textViewBookTitle.setText("Please enter a search term");
            } else {
                textViewBookAuthor.setText("");
                textViewBookTitle.setText("Please check your network connection and try again.");
            }
        }

    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {

        String queryString = "";

        if (args != null) {
            queryString = args.getString("query");
        }


        return new FetchBookLoader(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemArray = jsonObject.getJSONArray("items");

            int i = 0;
            String title = null;
            String authors = null;

            while (i < itemArray.length() && authors == null) {
                JSONObject book = itemArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                title = volumeInfo.getString("title");
                authors = volumeInfo.getString("authors");

                i++;
            }

            if (title != null) {
                textViewBookTitle.setText(title);
                textViewBookAuthor.setText(authors);
            } else {
                textViewBookTitle.setText(getResources().getString(R.string.no_result_found));
                textViewBookAuthor.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
            textViewBookTitle.setText(e.getLocalizedMessage());
            textViewBookAuthor.setText("");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }


    @Override
    void initialize() {

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        initializeView();
        setListeners();

    }

    @Override
    void initializeView() {

        textViewLabel = findViewById(R.id.textViewLabel);
        editTextBookInput = findViewById(R.id.editTextBookInput);
        buttonSearchBook = findViewById(R.id.buttonSearchBook);
        textViewBookTitle = findViewById(R.id.textViewBookTitle);
        textViewBookAuthor = findViewById(R.id.textViewBookAuthor);

    }

    @Override
    void setListeners() {
        buttonSearchBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSearchBook) {
            searchBooks(v);
        }
    }
}
