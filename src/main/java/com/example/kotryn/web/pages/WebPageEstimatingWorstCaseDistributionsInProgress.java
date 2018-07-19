package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingWorstCaseDistributionsInProgress {

    private final Long jobId;

    public WebPageEstimatingWorstCaseDistributionsInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-start-page", "http://localhost:8080/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Title("title", "h3", "Estimating worst case distributions in progress")));
        body.add(new Entity<>(new Button("button-back", "/estimating_worst_case_distributions_in_progress_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button", "/estimating_worst_case_distributions_in_progress/"+jobId, "Refresh")));

        return new Page(new Header(header), new Body(body));
    }
}
