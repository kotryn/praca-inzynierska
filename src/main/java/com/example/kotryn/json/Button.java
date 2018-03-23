package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Button {
    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String url;

    @JsonView(View.Summary.class)
    private String title;

    public Button() {
    }

    public Button(String type, String url, String title) {
        this.type = type;
        this.url = url;
        this.title = title;
    }

    /*public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return url;
    }

    public void setText(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }*/
}
