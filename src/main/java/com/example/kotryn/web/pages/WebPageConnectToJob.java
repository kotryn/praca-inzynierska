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
        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/start_page", "Start page")));

        if(error != null){
            body.add(new Item<>(new Text("text", error)));
        }
        body.add(new Item<>(new Input("input", new String[]{"id"}, new String[]{"Supply job ID:"})));
        body.add(new Item<>(new Button("button-back", "/start_page", "Back")));
        body.add(new Item<>(new Button("button-form", "/jobsPOST", "Next")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
