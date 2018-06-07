package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageCalculatingStatisticInProgress {
    private final Long jobId;

    public WebPageCalculatingStatisticInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/start_page", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "Calculating out-of sample statistic in progress")));
        body.add(new Item<>(new Button("button-back", "/calculating_statistic_in_progress_back/"+jobId, "Back")));
        body.add(new Item<>(new Button("button", "/calculating_statistic_in_progress/"+jobId, "Refresh")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
