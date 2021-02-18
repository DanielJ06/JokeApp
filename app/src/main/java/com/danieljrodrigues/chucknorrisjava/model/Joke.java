package com.danieljrodrigues.chucknorrisjava.model;

import com.google.gson.annotations.SerializedName;

public class Joke {
    @SerializedName("value")
    private final String value;

    public Joke(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
