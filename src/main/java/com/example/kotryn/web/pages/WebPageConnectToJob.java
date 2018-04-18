package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPageConnectToJob {

    private String error;

    public WebPageConnectToJob(String error) {
        this.error = error;
    }

    public Page show() {
        Form form = new Form("form", new String[]{"id"}, new String[]{"Supply job ID:"});

        Button btnBack = new Button("button-back", "/", "back");
        Button btnDelete = new Button("button-delete", "/", "Start page");
        Button btnForm = new Button("button-form", "/jobsPOST", "submit");

        Item<Form> itemForm = new Item<>(form);

        Item<Button> itemBtnDelete = new Item<>(btnDelete);
        Item<Button> itemBtnBack = new Item<>(btnBack);
        Item<Button> itemBtnForm = new Item<>(btnForm);

        if(error != null){
            Text errorText = new Text("text", error);
            Item<Text> error = new Item<>(errorText);
            return new Page(new Body(error, itemForm, itemBtnBack, itemBtnForm, itemBtnDelete));
        }

        return new Page(new Body(itemForm, itemBtnBack, itemBtnForm, itemBtnDelete));
    }
}
