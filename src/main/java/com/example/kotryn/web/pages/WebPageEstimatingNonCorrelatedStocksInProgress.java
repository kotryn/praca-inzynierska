package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingNonCorrelatedStocksInProgress {
    private final Long jobId;

    public WebPageEstimatingNonCorrelatedStocksInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "Estimating non correlated stocks in progress")));
        body.add(new Item<>(new Button("button-back", "/estimating_non_correlated_stocks_in_progress_back/"+jobId, "back")));
        body.add(new Item<>(new Button("button", "/estimating_non_correlated_stocks_in_progress/"+jobId, "refresh")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}