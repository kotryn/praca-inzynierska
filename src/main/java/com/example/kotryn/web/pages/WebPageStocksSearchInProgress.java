package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPageStocksSearchInProgress {

    private final Long jobId;

    public WebPageStocksSearchInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        Text text = new Text("text", "Searching for stocks in progress");
        //Button btnConnect = new Button("button", "/jobsPOST/"+jobId, "connect");
        Button refresh = new Button("button", "/stocks_search_in_progress/"+jobId, "refresh");
        Button btnBack = new Button("button-back", "/jobsPOST/"+jobId, "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");
        Item<Text> item = new Item<>(text);
        Item<Button> item2 = new Item<>(refresh);
        Item<Button> item3 = new Item<>(btnDelete);
        Item<Button> item4 = new Item<>(btnBack);
        Body body = new Body(item, item4, item2, item3);

        return new Page(body);
    }
}
