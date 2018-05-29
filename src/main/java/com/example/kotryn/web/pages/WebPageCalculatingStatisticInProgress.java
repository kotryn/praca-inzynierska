package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPageCalculatingStatisticInProgress {
    private final Long jobId;

    public WebPageCalculatingStatisticInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        Text text = new Text("text", "Calculating out-of sample statistic in progress");

        Button btnRefresh = new Button("button", "/calculating_statistic_in_progress/"+jobId, "refresh");
        Button btnBack = new Button("button-back", "/calculating_statistic_in_progress_back/"+jobId, "back");
        Button btnDelete = new Button("button", "/prompt_user", "Start page");

        Item<Text> itemText = new Item<>(text);

        Item<Button> itemBtnRefresh = new Item<>(btnRefresh);
        Item<Button> itemBtnBack = new Item<>(btnBack);
        Item<Button> itemBtnDelete = new Item<>(btnDelete);

        return new Page(new Body(itemText, itemBtnBack, itemBtnRefresh, itemBtnDelete));
    }
}
