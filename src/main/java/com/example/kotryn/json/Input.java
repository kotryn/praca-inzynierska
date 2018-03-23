package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Input {
    @JsonView(View.Summary.class)
    private String[] values;

    @JsonView(View.Summary.class)
    private String[] names;

    public Input(String[] values, String[] names) {
        this.values = values;
        this.names = names;
    }
}
