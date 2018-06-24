package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingWorstCaseCopulaInProgress {
    private final Long jobId;

    public WebPageEstimatingWorstCaseCopulaInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        List<Entity> body = new ArrayList<>();
        List<Entity> navbar = new ArrayList<>();

        navbar.add(new Entity<>(new Button("button-start-page", "/start_page", "Start page")));
        navbar.add(new Entity<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Entity<>(new Title("title", "h3", "Estimating worst case copula in progress")));
        body.add(new Entity<>(new Button("button-back", "/estimating_worst_case_copula_in_progress_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button", "/estimating_worst_case_copula_in_progress/"+jobId, "Refresh")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
