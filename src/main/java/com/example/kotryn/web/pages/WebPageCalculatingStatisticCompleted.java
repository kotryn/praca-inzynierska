package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WebPageCalculatingStatisticCompleted {
    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageCalculatingStatisticCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
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

        List<String> statistic = Optional.ofNullable(job.getStatistic()).orElse(Collections.singletonList("none"));

        body.add(new Item<>(new Text("text", "Calculating out-of sample statistic completed successful")));
        body.add(new Item<>( new Text("text", "Result: ")));

        body.add(new Item<>(new ListJ("list", statistic)));

        body.add(new Item<>(new Button("button-back", "/calculating_statistic_in_progress_completed_back/"+jobId, "Back")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}