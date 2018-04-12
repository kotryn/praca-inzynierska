package com.example.kotryn.web.pages;

import com.example.kotryn.controller.MainController;

import static java.lang.System.exit;

public class WebPageDeleteConfirmation {

    private final Long jobId;
    private final MainController controller;

    public WebPageDeleteConfirmation(Long jobId, MainController controller) {
        this.jobId = jobId;
        this.controller = controller;
        show();
    }

    private void show() {
        /*view.println("Deleted job ID: " + jobId);
        view.println("1. Begin or connect to a job (link)");
        view.println("2. Exit");
        switch (view.nextInt()) {
            case 1:
                controller.promptUserGET();
                break;
            case 2:
                exit(0);
                break;
            default:
                throw new RuntimeException("Undefined option");
        }*/
    }
}
