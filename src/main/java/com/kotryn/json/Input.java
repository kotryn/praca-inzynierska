package com.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Input {

    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String kind;

    @JsonView(View.Summary.class)
    private String[] values;

    @JsonView(View.Summary.class)
    private String[] names;


    public Input(String type, String kind, String[] values, String[] names) {
        this.values = values;
        this.names = names;
        this.type = type;
        this.kind = kind;
    }
}
