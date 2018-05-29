package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

public class WebPageDeleteJob {

    private String error;

    public WebPageDeleteJob(String error) {
        this.error = error;
    }

    public Page show() {
        Text title = new Text("text", "Delete job");
        Input input = new Input("input", new String[]{"id"}, new String[]{"Supply job ID:"});

        Button btnBack = new Button("button-back", "/", "back");
        Button btnDelete = new Button("button", "/prompt_user", "Start page");
        Button btnForm = new Button("button-delete", "/delete_job", "Delete");

        Item<Text> itemTitle = new Item<>(title);
        Item<Input> itemForm = new Item<>(input);

        Item<Button> itemBtnDelete = new Item<>(btnDelete);
        Item<Button> itemBtnBack = new Item<>(btnBack);
        Item<Button> itemBtnForm = new Item<>(btnForm);

        if(error != null){
            Text errorText = new Text("text", error);
            Item<Text> error = new Item<>(errorText);
            return new Page(new Body(itemTitle, error, itemForm, itemBtnBack, itemBtnForm, itemBtnDelete));
        }

        return new Page(new Body(itemTitle, itemForm, itemBtnBack, itemBtnForm, itemBtnDelete));
    }
}
