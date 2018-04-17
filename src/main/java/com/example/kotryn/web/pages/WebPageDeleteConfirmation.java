package com.example.kotryn.web.pages;

import com.example.kotryn.json.Page;

public class WebPageDeleteConfirmation {

    private final Long jobId;

    public WebPageDeleteConfirmation(Long jobId) {
        this.jobId = jobId;
    }

    private Page show() {
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
        return null;
    }
}
