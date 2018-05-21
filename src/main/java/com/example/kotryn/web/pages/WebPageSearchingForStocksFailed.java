package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPageSearchingForStocksFailed {
    private final Long jobId;

    public WebPageSearchingForStocksFailed(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        Text text = new Text("text", "WebPageSearchingForStocksFailed "+ jobId);

        //Button btnConnect = new Button("button", "/jobsPOST/"+jobId, "connect");
        //Button btnBack = new Button("button-back", "/", "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");

        Item<Text> itemText = new Item<>(text);

        //Item<Button> itemBtnConnect = new Item<>(btnConnect);
        //Item<Button> itemBtnBack = new Item<>(btnBack);
        Item<Button> itemBtnDelete = new Item<>(btnDelete);

        return new Page(new Body(itemText, itemBtnDelete));
    }
}