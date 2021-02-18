package com.danieljrodrigues.chucknorrisjava.datasource;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRemoteDataSource {

    public interface ListCategoriesCallback {
        void onSuccess(List<String> response);

        void onError(String message);

        void onComplete();
    }

    public void findAll(ListCategoriesCallback cb) {
        HTTPClient.retrofit().create(ChuckNorrisApi.class)
        .findAll()
        .enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    cb.onSuccess(response.body());
                }
                cb.onComplete();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                cb.onError(t.getMessage());
                cb.onComplete();
            }
        });
    }
}
