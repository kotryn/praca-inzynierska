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

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));

        if(error != null){
            body.add(new Item<>(new Text("text", error)));
        }
        body.add(new Item<>(new Input("input", new String[]{"id"}, new String[]{"Supply job ID:"})));
        body.add(new Item<>(new Button("button-back", "/", "Back")));
        body.add(new Item<>(new Button("button-form", "/jobsPOST", "submit")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
