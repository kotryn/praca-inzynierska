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
        List<Entity> body = new ArrayList<>();
        List<Entity> navbar = new ArrayList<>();

        navbar.add(new Entity<>(new Button("button-start-page", "/start_page", "Start page")));
        navbar.add(new Entity<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Entity<>(new Title("title", "h3", "Estimating growth stocks in progress")));
        body.add(new Entity<>(new Button("button-back", "/estimating_growth_stocks_in_progress_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button", "/estimating_growth_stocks_in_progress/"+jobId, "Refresh")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
