package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageBuildingRobustPortfolioFailed {
    private final ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageBuildingRobustPortfolioFailed(Long jobId, ProcessDescriptorRepository processDescriptorRepository) {
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://Lenovo-320:8081//start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Building robust portfolio failed")));
        body.add(new Entity<>(new Button("button-back", "http://Lenovo-320:8081//building_robust_portfolio_failed_back/"+jobId, "Start page")));

        return new Page(new Header(header), new Body(body));
    }
}
