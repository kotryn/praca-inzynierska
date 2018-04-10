package com.example.kotryn.web.pages;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.json.Body;
import com.example.kotryn.json.Button;
import com.example.kotryn.json.Item;
import com.example.kotryn.json.Page;

public class WebPagePromptUser {

    private final MainController controller;

    public WebPagePromptUser(MainController controller) {
        this.controller = controller;
    }

    public Page show() {
        /*view.println("What would you like to do?");
        view.println("1. Begin a new job (link)");
        view.println("2. Connect to a job (link)");
        view.println("3. Exit");
        switch (view.nextInt()) {
            case 1:
                controller.beginJobGET();
                break;
            case 2:
                controller.connectToJobGET();
                break;
            case 3:
                exit(0);
                break;
            default:
                throw new RuntimeException("Undefined option");
        }*/
        Button newJob = new Button("button", "/begin_job", "Begin a new job");
        Button connectJob = new Button("button", "/connect_to_job", "Connect to a job");

        Item<Button> newJobI = new Item<>(newJob);
        Item<Button> connectJobI = new Item<>(connectJob);

        Body loginBody = new Body(newJobI, connectJobI);

        return new Page(loginBody);
    }
}
