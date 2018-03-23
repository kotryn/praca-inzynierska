package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Page {

    @JsonView(View.Summary.class)
    private Long id;

    @JsonView(View.Summary.class)
    private Body body;

    public Page(Long id, Body body) {
        this.id = id;
        this.body = body;
    }

    public Long setId(Long id) {
        return this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }
}
