package com.danieljrodrigues.chucknorrisjava.presenter;

import android.os.Handler;

import com.danieljrodrigues.chucknorrisjava.MainActivity;
import com.danieljrodrigues.chucknorrisjava.datasource.CategoryRemoteDataSource;
import com.danieljrodrigues.chucknorrisjava.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter implements CategoryRemoteDataSource.ListCategoriesCallback {
    private final MainActivity view;
    private final CategoryRemoteDataSource dataSource;

    public CategoryPresenter(MainActivity mainActivity, CategoryRemoteDataSource dataSource) {
        this.view = mainActivity;
        this.dataSource = dataSource;
    }

    public void requestAll() {
        this.view.showProgressbar();
        this.dataSource.findAll(this);
    }

    @Override
    public void onSuccess(List<String> response) {
        List<CategoryItem> categoryItems = new ArrayList<>();
        for(String val : response) {
            categoryItems.add(new CategoryItem(val));
        }
        view.showCategories(categoryItems);
    }

    @Override
    public void onError(String message) {
        this.view.showFailure(message);
    }

    public void onComplete() {
        view.hideProgressbar();
    }
}
