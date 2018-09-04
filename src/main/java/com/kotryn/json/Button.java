package com.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Button {
    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String url;

    @JsonView(View.Summary.class)
    private String name;

    public Button(String type, String url, String name) {
        this.type = type;
        this.url = url;
        this.name = name;
    }
}
