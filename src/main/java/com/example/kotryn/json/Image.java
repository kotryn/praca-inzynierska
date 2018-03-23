package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Image {

    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String src;

    public Image() {
    }

    public Image(String type, String src) {
        this.type = type;
        this.src = src;
    }

    /*public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }*/
}
