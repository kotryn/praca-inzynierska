package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingGrowthStocksInProgress {
    private final Long jobId;

    public WebPageEstimatingGrowthStocksInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "Estimating growth stocks in progress")));
        body.add(new Item<>(new Button("button-back", "/estimating_growth_stocks_in_progress_back/"+jobId, "back")));
        body.add(new Item<>(new Button("button", "/estimating_growth_stocks_in_progress/"+jobId, "refresh")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
