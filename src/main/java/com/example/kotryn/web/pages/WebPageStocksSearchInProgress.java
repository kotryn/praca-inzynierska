package com.example.kotryn.web.pages;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.json.*;

public class WebPageStocksSearchInProgress {

    private final Long jobId;
    private final MainController controller;

    public WebPageStocksSearchInProgress(Long jobId, MainController controller) {
        this.jobId = jobId;
        this.controller = controller;
    }

    public Page show() {
       /* view.println("Searching for stocks in progress");
        view.println("1. Interrupt (link)");
        view.println("2. Refresh (link)");
        view.println("3. Exit");
        WebDataSearchingForStocksInProgress webData = new WebDataSearchingForStocksInProgress(jobId);
        switch (view.nextInt()) {
            case 1:
                webData.setAction(Action.INTERRUPT);
                controller.searchingForStocksInProgressPOST(webData);
                // once 201 is received for POST, browser connects:
                controller.jobsGET(jobId);
                break;
            case 2:
                webData.setAction(Action.REFRESH);
                controller.searchingForStocksInProgressPOST(webData);
                // once 201 is received for POST, browser connects:
                controller.jobsGET(jobId);
                break;
            case 3:
                exit(0);
                break;
            default:
                throw new RuntimeException("Undefined option");
        }*/
        Text text = new Text("text", "Searching for stocks in progress");
        //Button btnConnect = new Button("button", "/jobsPOST/"+jobId, "connect");
        Button refresh = new Button("button", "/stocks_search_in_progress/"+jobId, "refresh");
        Button btnBack = new Button("button-back", "/", "back");
        Item<Text> item = new Item<>(text);
        Item<Button> item2 = new Item<>(refresh);
        Item<Button> item3 = new Item<>(btnBack);
        Body body = new Body(item, item2, item3);

        return new Page(body);
    }
}
