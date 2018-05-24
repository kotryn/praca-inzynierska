package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class ListJ {
    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private List<String> items;

    public ListJ(String type, List<String> items) {
        this.type = type;
        this.items = items;
    }
}

