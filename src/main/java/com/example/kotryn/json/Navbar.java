package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Arrays;
import java.util.List;

public class Navbar {

    @JsonView(View.Summary.class)
    private List<Item> items;

    public Navbar(Item ...items) {
        this.items = Arrays.asList(items);
    }

    public Navbar(List<Item> items) {
        this.items = items;
    }
}
