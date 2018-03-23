package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Item<T> {

    @JsonView(View.Summary.class)
    private T item;

    /*public Item() {

    }*/

    public Item(T item) {
        this.item = item;
    }

    /*public T getItems() {
        return item;
    }

    public void setItems(T item) {
        this.item = item;
    }*/
}
