package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;
import com.example.kotryn.repository.ProcessDescriptorRepository;

public class WebPageBuildingRobustPortfolioFailed {
    private final ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageBuildingRobustPortfolioFailed(Long jobId, ProcessDescriptorRepository processDescriptorRepository) {
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Text text = new Text("text", "Building robust portfolio failed "+ jobId); //TODO: failed

        //Button btnConnect = new Button("button", "/jobsPOST/"+jobId, "connect");
        //Button btnBack = new Button("button-back", "/", "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");

        Item<Text> itemText = new Item<>(text);

        //Item<Button> itemBtnConnect = new Item<>(btnConnect);
        //Item<Button> itemBtnBack = new Item<>(btnBack);
        Item<Button> itemBtnDelete = new Item<>(btnDelete);

        return new Page(new Body(itemText, itemBtnDelete));
    }
}