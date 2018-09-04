package com.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Page {
    @JsonView(View.Summary.class)
    private Header header;
    @JsonView(View.Summary.class)
    private Body body;

    public Page(Header header, Body body) {
        this.header = header;
        this.body = body;
    }

    public Page(Body body) {
        this.body = body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}
