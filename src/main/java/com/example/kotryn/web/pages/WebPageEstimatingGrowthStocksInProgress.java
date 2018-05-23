package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPageEstimatingGrowthStocksInProgress {
    private final Long jobId;

    public WebPageEstimatingGrowthStocksInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        Text text = new Text("text", "Estimating growth stocks in progress");

        Button btnRefresh = new Button("button", "/estimating_growth_stocks_in_progress/"+jobId, "refresh");
        Button btnBack = new Button("button-back", "/estimating_growth_stocks_in_progress_back/"+jobId, "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");

        Item<Text> itemText = new Item<>(text);

        Item<Button> itemBtnRefresh = new Item<>(btnRefresh);
        Item<Button> itemBtnDelete = new Item<>(btnDelete);
        Item<Button> itemBtnBack = new Item<>(btnBack);

        return new Page(new Body(itemText, itemBtnBack, itemBtnRefresh, itemBtnDelete));
    }
}
