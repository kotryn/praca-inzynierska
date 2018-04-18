package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Form {

    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String[] values;

    @JsonView(View.Summary.class)
    private String[] names;


    public Form(String type, String[] values, String[] names) {
        this.values = values;
        this.names = names;
        this.type = type;
    }
}
