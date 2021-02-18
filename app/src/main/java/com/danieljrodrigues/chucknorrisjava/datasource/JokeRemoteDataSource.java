package com.danieljrodrigues.chucknorrisjava.datasource;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.JsonToken;

import com.danieljrodrigues.chucknorrisjava.model.Joke;
import com.danieljrodrigues.chucknorrisjava.presenter.JokePresenter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JokeRemoteDataSource {

    public interface JokeCallback {
        void onSuccess(Joke response);

        void onError(String message);

        void onComplete();
    }

    public void findJokeBy(JokeCallback cb, String categoryName) {
        HTTPClient.retrofit().create(ChuckNorrisApi.class)
            .findRandomBy(categoryName)
            .enqueue(new Callback<Joke>() {
                @Override
                public void onResponse(Call<Joke> call, Response<Joke> response) {
                    if (response.isSuccessful()) {
                        cb.onSuccess(response.body());
                    }
                    cb.onComplete();
                }

                @Override
                public void onFailure(Call<Joke> call, Throwable t) {
                    cb.onError(t.getMessage());
                    cb.onComplete();
                }
            });
    }
}
