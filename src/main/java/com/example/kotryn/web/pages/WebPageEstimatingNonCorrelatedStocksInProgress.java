package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPageEstimatingNonCorrelatedStocksInProgress {
    private final Long jobId;

    public WebPageEstimatingNonCorrelatedStocksInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        Text text = new Text("text", "WebPageEstimatingNonCorrelatedStocksInProgress "+ jobId);

        Button btnRefresh = new Button("button", "/estimating_non_correlated_stocks_in_progress/"+jobId, "refresh");
        Button btnBack = new Button("button-back", "/estimating_non_correlated_stocks_in_progress_back/"+jobId, "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");

        Item<Text> itemText = new Item<>(text);


        Item<Button> itemBtnRefresh = new Item<>(btnRefresh);
        Item<Button> itemBtnBack = new Item<>(btnBack);
        Item<Button> itemBtnDelete = new Item<>(btnDelete);

        return new Page(new Body(itemText, itemBtnBack, itemBtnRefresh, itemBtnDelete));
    }
}