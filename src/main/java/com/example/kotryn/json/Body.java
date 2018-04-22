package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Arrays;
import java.util.List;

public class Body {

    @JsonView(View.Summary.class)
    private List<Item> items;

    public Body(Item ...items) {
        this.items = Arrays.asList(items);
    }

    public Body(List<Item> items) {
        this.items = items;
    }
}
