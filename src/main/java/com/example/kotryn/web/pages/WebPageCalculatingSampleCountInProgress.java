package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPageCalculatingSampleCountInProgress {

    private final Long jobId;

    public WebPageCalculatingSampleCountInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        Text text = new Text("text", "Calculating Sample Count in progress");

        Button btnRefresh = new Button("button", "/calculating_sample_count_in_progress/"+jobId, "refresh");
        Button btnBack = new Button("button-back", "/calculating_sample_count_in_progress_back/"+jobId, "back");
        Button btnDelete = new Button("button", "/prompt_user", "Start page");

        Item<Text> itemText = new Item<>(text);

        Item<Button> itemBtnRefresh = new Item<>(btnRefresh);
        Item<Button> itemBtnDelete = new Item<>(btnDelete);
        Item<Button> itemBtnBack = new Item<>(btnBack);

        return new Page(new Body(itemText, itemBtnBack, itemBtnRefresh, itemBtnDelete));
    }
}
