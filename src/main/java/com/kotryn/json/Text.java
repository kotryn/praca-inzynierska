package com.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Text {

    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String text;

    public Text(String type, String text) {
        this.type = type;
        this.text = text;
    }
}
