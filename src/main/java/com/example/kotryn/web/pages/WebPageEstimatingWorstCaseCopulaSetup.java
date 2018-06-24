package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingWorstCaseCopulaSetup {
    private JobRepository jobRepository;
    private final Long jobId;

    public WebPageEstimatingWorstCaseCopulaSetup(JobRepository jobRepository, Long jobId) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Entity> body = new ArrayList<>();
        List<Entity> navbar = new ArrayList<>();

        navbar.add(new Entity<>(new Button("button-start-page", "/start_page", "Start page")));
        navbar.add(new Entity<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Estimating worst case copula setup")));

        if(job.getCopulaWindowSize() != null){
            body.add(new Entity<>(new Text("text", "Previously selected window size: " + job.getCopulaWindowSize())));
        }
        body.add(new Entity<>(new Input("input-integer", new String[]{"copulaWindowSize"}, new String[]{"Window size:"})));

        body.add(new Entity<>(new Text("text", "Previously selected type: " + job.getCopulaType())));

        body.add(new Entity<>(new Text("text", "Copula type:")));
        body.add(new Entity<>(new RadioButton("radio-button", new String[]{"Clayton copula", "t copula"}, new String[]{"Clayton copula", "t copula"}, "copulaType")));


        body.add(new Entity<>(new Button("button-back", "/estimating_worst_case_copula_setup_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button-form", "/estimating_worst_case_copula/"+jobId, "Next")));

        return new Page(new Navbar(navbar) ,new Body(body));
    }
}
