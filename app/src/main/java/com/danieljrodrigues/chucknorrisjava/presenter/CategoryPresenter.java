package com.danieljrodrigues.chucknorrisjava.presenter;

import android.os.Handler;

import com.danieljrodrigues.chucknorrisjava.MainActivity;
import com.danieljrodrigues.chucknorrisjava.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter {
    private final MainActivity view;
    private static List<CategoryItem> items = new ArrayList<>();

    static {
        items.add(new CategoryItem("Catg1"));
        items.add(new CategoryItem("Catg2"));
        items.add(new CategoryItem("Catg3"));
        items.add(new CategoryItem("Catg4"));
        items.add(new CategoryItem("Catg5"));
        items.add(new CategoryItem("Catg6"));
    }

    public CategoryPresenter(MainActivity mainActivity) {
        this.view = mainActivity;
    }

    public void requestAll() {
        // Request
        this.view.showProgressbar();
        this.request();
    }

    public void onSuccess(List<CategoryItem> data) {
        view.showCategories(data);
        onComplete();
    }

    public void onComplete() {
        view.hideProgressbar();
    }

    public void request() {
        new Handler().postDelayed(() -> {
            onSuccess(items);
        }, 3000);
    }
}
