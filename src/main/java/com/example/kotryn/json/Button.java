package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Button {
    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String url;

    @JsonView(View.Summary.class)
    private String title;

    public Button(String type, String url, String title) {
        this.type = type;
        this.url = url;
        this.title = title;
    }
}
