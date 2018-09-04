package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingWorstCaseCopulaFailed {
    private final Long jobId;

    public WebPageEstimatingWorstCaseCopulaFailed(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://localhost:8080/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Estimating worst case copula failed")));
        body.add(new Entity<>(new Button("button-back", "http://localhost:8080/estimating_worst_case_copula_failed_back/"+jobId, "Back")));

        return new Page(new Header(header), new Body(body));
    }
}
