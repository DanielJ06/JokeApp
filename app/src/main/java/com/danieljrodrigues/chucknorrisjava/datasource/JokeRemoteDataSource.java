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

public class JokeRemoteDataSource {

    public interface JokeCallback {
        void onSuccess(Joke response);

        void onError(String message);

        void onComplete();
    }

    public void findJokeBy(JokeCallback cb, String categoryName) {
        new JokeTask(cb, categoryName).execute();
    }

    private static class JokeTask extends AsyncTask<Void, Void, Joke> {

        private final String category;
        private final JokeCallback callback;
        private String errorMessage;

        public JokeTask(JokeCallback cb, String category) {
            this.callback = cb;
            this.category = category;
        }

        @Override
        protected Joke doInBackground(Void... voids) {
            Joke joke = null;
            HttpsURLConnection urlConnection = null;

            try {
                String endpoint = String.format("%s?category=%s", Endpoint.GET_JOKE, category);
                URL url = new URL(endpoint);
                urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setReadTimeout(2000);
                urlConnection.setConnectTimeout(3000);

                int responseCode = urlConnection.getResponseCode();
                if(responseCode > 400) {
                    throw new IOException("Erro ao conectar com o servidor");
                }

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                JsonReader jsonReader = new JsonReader(new InputStreamReader(in));

                jsonReader.beginObject();

                String value = null;

                while (jsonReader.hasNext()) {
                    JsonToken token = jsonReader.peek();

                    if(token == JsonToken.NAME) {
                        String name = jsonReader.nextName();
                        if (name.equals("value")) {
                            value = jsonReader.nextString();
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                }

                joke = new Joke(value);
                jsonReader.endObject();

            } catch (IOException e) {
                errorMessage = e.getMessage();
            }

            return joke;
        }

        @Override
        protected void onPostExecute(Joke joke) {
            if (errorMessage != null) {
                callback.onError(errorMessage);
            } else {
                callback.onSuccess(joke);
            }
            callback.onComplete();
        }
    }
}
