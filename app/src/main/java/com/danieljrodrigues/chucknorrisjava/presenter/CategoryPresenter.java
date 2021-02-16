package com.danieljrodrigues.chucknorrisjava.presenter;

import com.danieljrodrigues.chucknorrisjava.MainActivity;
import com.danieljrodrigues.chucknorrisjava.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter {
    private final MainActivity mainActivity;
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
        this.mainActivity = mainActivity;
    }

    public void requestAll() {
        // Request
        this.request();
    }

    public void request() {
        mainActivity.showCategories(items);
    }
}
