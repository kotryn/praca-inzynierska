package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Page {

    @JsonView(View.Summary.class)
    private Body body;

    public Page(Body body) {
        this.body = body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }
}
