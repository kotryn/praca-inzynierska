package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPageBeginJob {

    private final Long jobId;

    public WebPageBeginJob(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        Text text = new Text("text", "New job id: "+ jobId);
        Button btnConnect = new Button("button", "/jobsPOST/"+jobId, "connect");
        Button btnBack = new Button("button-back", "/", "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");
        Item<Text> item = new Item<>(text);
        Item<Button> item2 = new Item<>(btnConnect);
        Item<Button> item3 = new Item<>(btnBack);
        Item<Button> item4 = new Item<>(btnDelete);
        Body body = new Body(item, item2, item3, item4);

        return new Page(body);
    }
}
