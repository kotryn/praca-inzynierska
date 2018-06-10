package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class Table {
    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private List<List<String>> data;

    public Table(String type, List<List<String>> data) {
        this.type = type;
        this.data = data;
    }
}
