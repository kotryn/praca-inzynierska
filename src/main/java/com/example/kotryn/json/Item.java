package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Item<T> {

    @JsonView(View.Summary.class)
    private T item;

    public Item(T item) {
        this.item = item;
    }
}
