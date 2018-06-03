package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingWorstCaseDistributionsSetup {
    private JobRepository jobRepository;
    private final Long jobId;

    public WebPageEstimatingWorstCaseDistributionsSetup(JobRepository jobRepository, Long jobId) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "Estimating worst case distributions setup")));

        body.add(new Item<>(new Text("text", "Previously selected window size: " + job.getWindowSize())));
        body.add(new Item<>(new Input("input-integer", new String[]{"windowSize"}, new String[]{"Window Size:"})));

        body.add(new Item<>(new Text("text", "Previously selected required growth rate: " + job.getGrowthRate())));
        body.add(new Item<>(new Input("input-integer", new String[]{"growthRate"}, new String[]{"Required growth rate:"})));

        body.add(new Item<>(new Button("button-back", "/estimating_worst_case_distributions_setup_back/"+jobId, "back")));
        body.add(new Item<>(new Button("button-form", "/estimating_worst_case_distributions/"+jobId, "Next")));

        return new Page(new Navbar(navbar) ,new Body(body));
    }
}

