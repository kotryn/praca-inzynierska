package com.example.kotryn.web.pages;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.json.Page;

public class WebPageStocksSearchInProgress {

    private final long jobId;
    private final MainController controller;

    public WebPageStocksSearchInProgress(long jobId, MainController controller) {
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
       return null;
    }
}
