package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Title {

    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String tag;

    @JsonView(View.Summary.class)
    private String text;

    public Title(String type, String tag, String text) {
        this.type = type;
        this.tag = tag;
        this.text = text;
    }
}
