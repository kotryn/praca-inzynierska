package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPageBuildingRobustPortfolioInProgress {
    private final Long jobId;

    public WebPageBuildingRobustPortfolioInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        Text text = new Text("text", "WebPageBuildingRobustPortfolioInProgress "+ jobId);

        Button btnRefresh = new Button("button", "/building_robust_portfolio_in_progress/"+jobId, "refresh");
        Button btnBack = new Button("button-back", "/building_robust_portfolio_in_progress_back/"+jobId, "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");

        Item<Text> itemText = new Item<>(text);

        Item<Button> itemBtnRefresh = new Item<>(btnRefresh);
        Item<Button> itemBtnBack = new Item<>(btnBack);
        Item<Button> itemBtnDelete = new Item<>(btnDelete);

        return new Page(new Body(itemText, itemBtnBack, itemBtnRefresh, itemBtnDelete));
    }
}