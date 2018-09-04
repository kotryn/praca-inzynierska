package com.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class StaticList {
    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private List<String> items;

    public StaticList(String type, List<String> items) {
        this.type = type;
        this.items = items;
    }
}

