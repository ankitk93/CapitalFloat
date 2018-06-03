package com.ankit.assignment.capitalfloat.model;

import android.os.Parcelable;

import java.io.Serializable;

public class Categories implements Serializable{

    private String categories;
    private boolean isSelected;


    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
