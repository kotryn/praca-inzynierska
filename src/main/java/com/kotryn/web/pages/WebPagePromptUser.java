package com.kotryn.web.pages;

import com.kotryn.json.*;

public class WebPagePromptUser {

    public WebPagePromptUser() {

    }

    public Page show() {
        Title text = new Title("title", "h2", "Robust portfolio creator");
        Button btnNewJob = new Button("button", "http://localhost:8080/begin_job", "Begin a new job");
        Button btnConnectJob = new Button("button", "http://localhost:8080/connect_to_job", "Connect to a job");
        Button btnDeleteJob = new Button("button", "http://localhost:8080/delete_job", "Delete job");

        Entity<Title> itemText = new Entity<>(text);
        Entity<Button> itemBtnNewJob = new Entity<>(btnNewJob);
        Entity<Button> itemBtnConnectJob = new Entity<>(btnConnectJob);
        Entity<Button> itemBtnDeleteJob = new Entity<>(btnDeleteJob);

        return new Page(new Body(itemText, itemBtnNewJob, itemBtnConnectJob, itemBtnDeleteJob));
    }
}
