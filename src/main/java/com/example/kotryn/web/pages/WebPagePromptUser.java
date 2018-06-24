package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPagePromptUser {

    public WebPagePromptUser() {

    }

    public Page show() {
        Button btnNewJob = new Button("button", "/begin_job", "Begin a new job");
        Button btnConnectJob = new Button("button", "/connect_to_job", "Connect to a job");
        Button btnDeleteJob = new Button("button", "/delete_job", "Delete job");

        Entity<Button> itemBtnNewJob = new Entity<>(btnNewJob);
        Entity<Button> itemBtnConnectJob = new Entity<>(btnConnectJob);
        Entity<Button> itemBtnDeleteJob = new Entity<>(btnDeleteJob);

        return new Page(new Body(itemBtnNewJob, itemBtnConnectJob, itemBtnDeleteJob));
    }
}
