package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingWorstCaseCopulaFailed {
    private final ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageEstimatingWorstCaseCopulaFailed(Long jobId, ProcessDescriptorRepository processDescriptorRepository) {
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {//TODO: failed
        List<Entity> body = new ArrayList<>();
        List<Entity> navbar = new ArrayList<>();

        navbar.add(new Entity<>(new Button("button-start-page", "/start_page", "Start page")));
        navbar.add(new Entity<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Estimating worst case copula failed")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
