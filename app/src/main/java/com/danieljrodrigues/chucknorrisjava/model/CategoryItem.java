package com.danieljrodrigues.chucknorrisjava.model;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.danieljrodrigues.chucknorrisjava.R;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

public class CategoryItem extends Item<ViewHolder> {

    private final String categoryTitle;

    public CategoryItem(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {
        TextView categoryTitleText = viewHolder.itemView.findViewById(R.id.category_item_tv);
        categoryTitleText.setText(categoryTitle);
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    @Override
    public int getLayout() {
        return R.layout.joke_category_item;
    }
}
