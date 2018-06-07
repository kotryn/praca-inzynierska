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

        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/start_page", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "Estimating worst case copula setup")));

        body.add(new Item<>(new Text("text", "Previously selected window size: " + job.getCopulaWindowSize())));
        body.add(new Item<>(new Input("input-integer", new String[]{"copulaWindowSize"}, new String[]{"Window size:"})));

        body.add(new Item<>(new Text("text", "Previously selected type: " + job.getCopulaType())));
        body.add(new Item<>(new Input("input", new String[]{"copulaType"}, new String[]{"Copula type:"})));

        body.add(new Item<>(new Button("button-back", "/estimating_worst_case_copula_setup_back/"+jobId, "Back")));
        body.add(new Item<>(new Button("button-form", "/estimating_worst_case_copula/"+jobId, "Next")));

        return new Page(new Navbar(navbar) ,new Body(body));
    }
}
