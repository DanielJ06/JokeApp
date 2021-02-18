package com.danieljrodrigues.chucknorrisjava;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.danieljrodrigues.chucknorrisjava.datasource.JokeRemoteDataSource;
import com.danieljrodrigues.chucknorrisjava.model.Joke;
import com.danieljrodrigues.chucknorrisjava.presenter.JokePresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class JokeActivity extends AppCompatActivity {

    public static final String CATEGORY_KEY = "category_key";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().getExtras() != null) {
            String categoryName = getIntent().getStringExtra(CATEGORY_KEY);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(categoryName);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                JokeRemoteDataSource dataSource = new JokeRemoteDataSource();
                JokePresenter jokePresenter = new JokePresenter(this, dataSource);
                jokePresenter.findJokeBy(categoryName);

                FloatingActionButton fab = findViewById(R.id.fab);
                fab.setOnClickListener(view -> {
                    jokePresenter.findJokeBy(categoryName);
                });
            }
        }

    }

    public void showJoke(Joke joke) {
        TextView jokeContent = findViewById(R.id.joke_tv_content);
        jokeContent.setText(joke.getValue());
    }

    public void showFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showProgressbar() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    public void hideProgressbar() {
        if (progressDialog != null) {
            progressDialog.hide();
        }
    }
}