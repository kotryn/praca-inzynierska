package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageCalculatingSampleCountSetup {

    private final Long jobId;
    private JobRepository jobRepository;

    public WebPageCalculatingSampleCountSetup(JobRepository jobRepository, Long jobId) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "In-sample and out-of-sample periods")));

        body.add(new Item<>(new Text("text", "Supply in-sample periods")));
        body.add(new Item<>(new Text("text", "Previous in-sample start date: " + job.getStartInSampleDate())));
        body.add(new Item<>(new Text("text", "Previous in-sample end date: " + job.getEndInSampleDate())));
        body.add(new Item<>(new Input("input-date", new String[]{"startInSampleDate", "endInSampleDate"}, new String[]{"Enter Start Date:", "Enter end date:"})));

        body.add(new Item<>(new Text("text", "Supply out-of-sample periods")));
        body.add(new Item<>(new Text("text", "Previous out-of-sample start date: " + job.getStartOutOfSampleDate())));
        body.add(new Item<>(new Text("text", "Previous out-of-sample end date: " + job.getEndOutOfSampleDate())));
        body.add(new Item<>(new Input("input-date", new String[]{"startOutOfSampleDate", "endOutOfSampleDate"}, new String[]{"Enter Start Date:", "Enter end date:"})));

        body.add(new Item<>(new Text("text", "Supply periodicity")));
        body.add(new Item<>(new Text("text", "Previous periodicity: " + job.getPeriodicity())));
        body.add(new Item<>(new Input("input", new String[]{"periodicity"}, new String[]{"Enter periodicity:"})));//TODO: radio button

        body.add(new Item<>(new Button("button-back", "/calculating_sample_count_setup_back/"+jobId, "Back")));
        body.add(new Item<>(new Button("button-form", "/calculating_sample_count/"+jobId, "Next")));




        return new Page(new Navbar(navbar), new Body(body));
    }
}