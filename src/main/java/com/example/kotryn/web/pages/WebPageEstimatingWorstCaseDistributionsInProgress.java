package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPageEstimatingWorstCaseDistributionsInProgress {

    private final Long jobId;

    public WebPageEstimatingWorstCaseDistributionsInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        Text text = new Text("text", "Estimating worst case distributions in progress");

        Button btnRefresh = new Button("button", "/estimating_worst_case_distributions_in_progress/"+jobId, "refresh");
        Button btnBack = new Button("button-back", "/estimating_worst_case_distributions_in_progress_back/"+jobId, "back");
        Button btnDelete = new Button("button", "/prompt_user", "Start page");

        Item<Text> itemText = new Item<>(text);

        Item<Button> itemBtnRefresh = new Item<>(btnRefresh);
        Item<Button> itemBtnDelete = new Item<>(btnDelete);
        Item<Button> itemBtnBack = new Item<>(btnBack);

        return new Page(new Body(itemText, itemBtnBack, itemBtnRefresh, itemBtnDelete));
    }
}
