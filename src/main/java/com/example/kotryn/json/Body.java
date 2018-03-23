package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Arrays;
import java.util.List;

public class Body {

    @JsonView(View.Summary.class)
    private List<Item> items;

    public Body() {

    }

    public Body(Item ...items) {
        this();
        this.items = Arrays.asList(items);
    }

    /*public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }*/
}
