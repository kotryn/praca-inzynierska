package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Page {
    @JsonView(View.Summary.class)
    private Navbar navbar;
    @JsonView(View.Summary.class)
    private Body body;

    public Page(Navbar navbar, Body body) {
        this.navbar = navbar;
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

    public Navbar getNavbar() {
        return navbar;
    }

    public void setNavbar(Navbar navbar) {
        this.navbar = navbar;
    }
}
