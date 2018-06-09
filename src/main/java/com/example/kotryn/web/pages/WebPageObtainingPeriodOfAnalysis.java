package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageObtainingPeriodOfAnalysis {

    private final JobRepository jobRepository;
    private final Long jobId;

    public WebPageObtainingPeriodOfAnalysis(Long jobId, JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.getOne(jobId);

        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/start_page", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "Supply the period of analysis")));
        body.add(new Item<>(new Text("text", "Previous start date: " + job.getStartDate())));
        body.add(new Item<>(new Text("text", "Previous end date: " + job.getEndDate())));
        body.add(new Item<>(new Input("input-date", new String[]{"startDate", "endDate"}, new String[]{"Enter Start Date:", "Enter end date:"})));
        body.add(new Item<>(new Button("button-form", "/jobSetDate/"+jobId, "Submit")));
        body.add(new Item<>(new Button("button-back", "/start_page", "Back")));
        body.add(new Item<>(new Button("button", "/period_of_analysis/"+jobId, "Next")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
