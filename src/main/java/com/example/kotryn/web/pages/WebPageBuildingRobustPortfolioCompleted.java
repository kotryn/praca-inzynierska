package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WebPageBuildingRobustPortfolioCompleted {
    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageBuildingRobustPortfolioCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        List<String> selectedRobustPortfolio = Optional.ofNullable(job.getRobustPortfolio()).orElse(Collections.singletonList("none"));
        List<String> previouslySelectedRobustPortfolio = Optional.ofNullable(job.getSelectedRobustPortfolio()).orElse(Collections.singletonList("none"));

        body.add(new Item<>(new Text("text", "Portfolio optimization completed successful")));
        body.add(new Item<>(new Text("text", "Previously: " + previouslySelectedRobustPortfolio)));
        body.add(new Item<>( new Text("text", "Available: ")));
        body.add(new Item<>(new Checkbox("checkbox", selectedRobustPortfolio, selectedRobustPortfolio)));
        body.add(new Item<>(new Button("button-back", "/building_robust_portfolio_in_progress_completed_back/"+jobId, "Back")));
        body.add(new Item<>(new Button("button-form", "/calculating_statistic/"+jobId, "submit")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
