package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Table {
    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String[] title;

    @JsonView(View.Summary.class)
    private String url;

    public Table(String type, String[] title, String url) {
        this.type = type;
        this.title = title;
        this.url = url;
    }
}
