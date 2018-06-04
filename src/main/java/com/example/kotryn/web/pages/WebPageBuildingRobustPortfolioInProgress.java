package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageBuildingRobustPortfolioInProgress {
    private final Long jobId;

    public WebPageBuildingRobustPortfolioInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "Portfolio optimization in progress")));
        body.add(new Item<>(new Button("button-back", "/building_robust_portfolio_in_progress_back/"+jobId, "Back")));
        body.add(new Item<>(new Button("button", "/building_robust_portfolio_in_progress/"+jobId, "Refresh")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}