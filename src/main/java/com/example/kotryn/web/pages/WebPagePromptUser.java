package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPagePromptUser {

    public WebPagePromptUser() {

    }

    public Page show() {
        Button btnNewJob = new Button("button", "/begin_job", "Begin a new job");
        Button btnConnectJob = new Button("button", "/connect_to_job", "Connect to a job");

        Item<Button> itemBtnNewJob = new Item<>(btnNewJob);
        Item<Button> itemBtnConnectJob = new Item<>(btnConnectJob);

        return new Page(new Body(itemBtnNewJob, itemBtnConnectJob));
    }
}
