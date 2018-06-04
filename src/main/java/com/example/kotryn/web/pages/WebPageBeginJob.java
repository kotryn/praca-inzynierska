package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageBeginJob {

    private final Long jobId;

    public WebPageBeginJob(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));

        body.add(new Item<>(new Text("text", "New job id: "+ jobId)));
        body.add(new Item<>(new Button("button-back", "/", "Back")));
        body.add(new Item<>(new Button("button", "/jobsPOST/"+jobId, "Connect")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
