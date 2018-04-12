package com.example.kotryn.web.pages;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.json.*;

public class WebPageBeginJob extends WebPageBase {

    private final Long jobId;
    private final MainController controller;

    public WebPageBeginJob(Long jobId, MainController controller) {
        this.jobId = jobId;
        this.controller = controller;
    }

    public Page show() {
        /*view.println("Your job ID: " + jobId);
        view.println("1. Connect to this job (link)");
        view.println("2. Exit");
        switchToJobsGET(view.nextInt(), jobId, controller);*/

        Text text = new Text("text", "New job id: "+ jobId);
        Button btnConnect = new Button("button", "/jobsPOST/"+jobId, "connect");
        Button btnBack = new Button("button-back", "/", "back");
        Item<Text> item = new Item<>(text);
        Item<Button> item2 = new Item<>(btnConnect);
        Item<Button> item3 = new Item<>(btnBack);
        Body body = new Body(item, item2, item3);

        return new Page(body);
    }
}
