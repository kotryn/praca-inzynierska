package com.kotryn.web.pages;

import com.kotryn.entity.Job;
import com.kotryn.json.*;
import com.kotryn.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageCalculatingSampleCountCompleted {

    private JobRepository jobRepository;
    private final Long jobId;

    public WebPageCalculatingSampleCountCompleted(Long jobId, JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://localhost:8080/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Calculating sample count completed successfully")));
        body.add(new Entity<>(new Text("text", "In-sample: "+job.getInSample())));
        body.add(new Entity<>( new Text("text", "Out-of-sample: "+job.getOutOfSample())));

        body.add(new Entity<>(new Button("button-back", "http://localhost:8080/calculating_sample_count_completed_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button", "http://localhost:8080/estimating_worst_case_distributions_setup/"+jobId, "Next")));

        return new Page(new Header(header), new Body(body));
    }
}