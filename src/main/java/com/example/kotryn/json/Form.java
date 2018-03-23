package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Form {

    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private Input input;

    @JsonView(View.Summary.class)
    private Button button;

    public Form(String type, Input input, Button button) {
        this.type = type;
        this.input = input;
        this.button = button;
    }
}
