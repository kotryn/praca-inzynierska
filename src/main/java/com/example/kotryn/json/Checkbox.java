package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class Checkbox {
    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private List<String> values;

    @JsonView(View.Summary.class)
    private List<String> names;

    public Checkbox(String type, List<String>values, List<String> names) {
        this.type = type;
        this.values = values;
        this.names = names;
    }
}

