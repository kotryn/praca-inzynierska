package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class ExpandedList {
    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String name;

    @JsonView(View.Summary.class)
    private List<Item> items;

    public ExpandedList(String type, String name, List<Item> items) {
        this.type = type;
        this.name = name;
        this.items = items;
    }
}

