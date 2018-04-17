package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPagePromptUser {

    public WebPagePromptUser() {

    }

    public Page show() {
        Button newJob = new Button("button", "/begin_job", "Begin a new job");
        Button connectJob = new Button("button", "/connect_to_job", "Connect to a job");

        Item<Button> newJobI = new Item<>(newJob);
        Item<Button> connectJobI = new Item<>(connectJob);

        Body loginBody = new Body(newJobI, connectJobI);

        return new Page(loginBody);
    }
}
