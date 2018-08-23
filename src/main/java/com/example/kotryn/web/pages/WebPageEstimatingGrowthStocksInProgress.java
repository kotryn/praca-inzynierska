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
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://Lenovo-320:8081//start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Title("title", "h3", "Estimating growth stocks in progress")));
        body.add(new Entity<>(new Button("button-back", "http://Lenovo-320:8081//estimating_growth_stocks_in_progress_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button", "http://Lenovo-320:8081//estimating_growth_stocks_in_progress/"+jobId, "Refresh")));

        return new Page(new Header(header), new Body(body));
    }
}
