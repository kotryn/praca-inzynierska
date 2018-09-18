package com.kotryn.web.pages;

import com.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageBuildingRobustPortfolioFailed {
    private final Long jobId;

    public WebPageBuildingRobustPortfolioFailed(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://kotryn.localtunnel.me/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Building robust portfolio failed")));
        body.add(new Entity<>(new Button("button-back", "http://kotryn.localtunnel.me/building_robust_portfolio_failed_back/"+jobId, "Start page")));

        return new Page(new Header(header), new Body(body));
    }
}
