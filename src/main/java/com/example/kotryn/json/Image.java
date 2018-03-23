package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Image {

    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String src;

    public Image(String type, String src) {
        this.type = type;
        this.src = src;
    }
}
