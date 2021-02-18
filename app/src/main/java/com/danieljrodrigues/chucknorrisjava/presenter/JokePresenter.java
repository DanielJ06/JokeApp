package com.danieljrodrigues.chucknorrisjava.presenter;

import com.danieljrodrigues.chucknorrisjava.JokeActivity;
import com.danieljrodrigues.chucknorrisjava.datasource.JokeRemoteDataSource;
import com.danieljrodrigues.chucknorrisjava.model.Joke;

public class JokePresenter implements JokeRemoteDataSource.JokeCallback {
    private final JokeActivity view;
    private final JokeRemoteDataSource dataSource;

    public JokePresenter(JokeActivity jokeActivity, JokeRemoteDataSource dataSource) {
        this.view = jokeActivity;
        this.dataSource = dataSource;
    }

    public void findJokeBy(String categoryName) {
        this.view.showProgressbar();
        this.dataSource.findJokeBy(this, categoryName);
    }

    @Override
    public void onSuccess(Joke response) {
        this.view.showJoke(response);
    }

    @Override
    public void onError(String message) {
        this.view.showFailure(message);
    }

    @Override
    public void onComplete() {
        this.view.hideProgressbar();
    }
}
