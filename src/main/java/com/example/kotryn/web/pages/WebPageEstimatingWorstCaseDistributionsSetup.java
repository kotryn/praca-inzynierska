package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingWorstCaseDistributionsSetup {
    private final Long jobId;

    public WebPageEstimatingWorstCaseDistributionsSetup(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "Estimating worst case distributions setup")));
        body.add(new Item<>(new Button("button-back", "/estimating_worst_case_distributions_setup_back/"+jobId, "back")));
        body.add(new Item<>(new Button("button", "/estimating_worst_case_distributions/"+jobId, "Next")));

        return new Page(new Navbar(navbar) ,new Body(body));
    }
}

