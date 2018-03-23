package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Text {

    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String text;

    public Text() {
    }

    public Text(String type, String text) {
        this.type = type;
        this.text = text;
    }

    /*public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }*/
}
