package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPageConnectToJob {

    public WebPageConnectToJob() {
    }

    public Page show() {
        Button btnForm = new Button("button", "/jobsPOST", "submit");
        Input inputForm = new Input(new String[]{"id"}, new String[]{"Supply job ID:"});
        Form form = new Form("form", inputForm, btnForm);

        Button btnBack = new Button("button-back", "/", "back");
        Button btnDelete = new Button("button-delete", "/", "Start page");

        Item<Form> item = new Item<>(form);
        Item<Button> item2 = new Item<>(btnDelete);
        Item<Button> item3 = new Item<>(btnBack);

        Body body = new Body(item, item3, item2);

        return new Page(body);
    }
}
