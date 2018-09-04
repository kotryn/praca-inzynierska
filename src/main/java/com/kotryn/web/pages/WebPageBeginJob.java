package com.kotryn.web.pages;

import com.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageBeginJob {

    private final Long jobId;

    public WebPageBeginJob(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "/start_page", "Start page")));

        body.add(new Entity<>(new Title("title", "h3","New job id: "+ jobId)));
        body.add(new Entity<>(new Button("button-back", "/start_page", "Back")));
        body.add(new Entity<>(new Button("button", "/jobsPOST/"+jobId, "Connect")));

        return new Page(new Header(header), new Body(body));
    }
}
