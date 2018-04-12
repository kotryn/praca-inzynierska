package com.example.kotryn.web.pages;


import com.example.kotryn.controller.MainController;
import com.example.kotryn.json.*;

public class WebPageConnectToJob extends WebPageBase {

    private final MainController controller;

    public WebPageConnectToJob(MainController controller) {
        this.controller = controller;
    }

    public Page show() {
        /*view.println("Supply job ID:");
        Long jobId = view.nextInt();
        view.println("1. Submit (link)");
        view.println("2. Exit");
        switchToJobsGET(view.nextInt(), jobId, controller);*/
        Button btnForm = new Button("button", "/jobsPOST", "submit");
        Input inputForm = new Input(new String[]{"id"}, new String[]{"Supply job ID:"});
        Form form = new Form("form", inputForm, btnForm);

        Button btnBack = new Button("button-back", "/", "back");

        Item<Form> item = new Item<>(form);
        Item<Button> item2 = new Item<>(btnBack);

        Body body = new Body(item, item2);

        return new Page(body);
    }
}
