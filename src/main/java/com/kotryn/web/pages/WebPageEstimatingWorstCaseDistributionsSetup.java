package com.kotryn.web.pages;

import com.kotryn.entity.Job;
import com.kotryn.json.*;
import com.kotryn.repository.JobRepository;

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

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://localhost:8080/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Estimating worst case distributions setup")));

        if(job.getWindowSize() != null){
            body.add(new Entity<>(new Text("text", "Previously selected window size: " + job.getWindowSize())));
        }
        body.add(new Entity<>(new Input("input", "number", new String[]{"windowSize"}, new String[]{"Window Size:"})));

        if(job.getGrowthRate() != null){
            body.add(new Entity<>(new Text("text", "Previously selected required growth rate: " + job.getGrowthRate())));
        }
        body.add(new Entity<>(new Input("input", "number", new String[]{"growthRate"}, new String[]{"Required growth rate:"})));

        body.add(new Entity<>(new Button("button-back", "/estimating_worst_case_distributions_setup_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button-form", "/estimating_worst_case_distributions/"+jobId, "Next")));

        return new Page(new Header(header) ,new Body(body));
    }
}

