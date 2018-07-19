package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class MainPage {
    @JsonView(View.Summary.class)
    private String name;

    @JsonView(View.Summary.class)
    private String url;

    public MainPage(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
