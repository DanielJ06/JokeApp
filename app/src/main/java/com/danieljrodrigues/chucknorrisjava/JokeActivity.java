package com.danieljrodrigues.chucknorrisjava;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class JokeActivity extends AppCompatActivity {

    public static final String CATEGORY_KEY = "category_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String categoryName = getIntent().getStringExtra(CATEGORY_KEY);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, categoryName, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });
    }
}