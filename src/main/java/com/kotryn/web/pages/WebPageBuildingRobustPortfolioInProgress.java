package com.kotryn.web.pages;

import com.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageBuildingRobustPortfolioInProgress {
    private final Long jobId;

    public WebPageBuildingRobustPortfolioInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://kotryn.localtunnel.me/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Title("title", "h3", "Portfolio optimization in progress")));
        body.add(new Entity<>(new Button("button-back", "http://kotryn.localtunnel.me/building_robust_portfolio_in_progress_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button", "/building_robust_portfolio_in_progress/"+jobId, "Refresh")));

        return new Page(new Header(header), new Body(body));
    }
}