package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageConnectToJob {

    private String error;

    public WebPageConnectToJob(String error) {
        this.error = error;
    }

    public Page show() {
        List<Entity> body = new ArrayList<>();
        List<Entity> navbar = new ArrayList<>();

        navbar.add(new Entity<>(new Button("button-start-page", "/start_page", "Start page")));

        if(error != null){
            body.add(new Entity<>(new Text("text", error)));
        }
        body.add(new Entity<>(new Input("input", new String[]{"id"}, new String[]{"Supply job ID:"})));
        body.add(new Entity<>(new Button("button-back", "/start_page", "Back")));
        body.add(new Entity<>(new Button("button-form", "/jobsPOST", "Next")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
