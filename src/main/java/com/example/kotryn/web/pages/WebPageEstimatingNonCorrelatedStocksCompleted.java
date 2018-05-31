package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WebPageEstimatingNonCorrelatedStocksCompleted {
    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageEstimatingNonCorrelatedStocksCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
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

        List<String> selectedNonCorrelatedStocks = Optional.ofNullable(job.getNonCorrelatedStocks()).orElse(Collections.singletonList("none"));
        List<String> previouslySelectedNonCorrelatedStocks  = Optional.ofNullable(job.getSelectedNonCorrelatedStocks()).orElse(Collections.singletonList("none"));

        body.add(new Item<>(new Text("text", "Estimating non correlated stocks completed successful")));
        body.add(new Item<>(new Text("text", "Previously: " + previouslySelectedNonCorrelatedStocks)));
        body.add(new Item<>( new Text("text", "Available: ")));

        body.add(new Item<>(new Checkbox("checkbox", selectedNonCorrelatedStocks, selectedNonCorrelatedStocks)));

        body.add(new Item<>(new Button("button-back", "/estimating_non_correlated_stocks_completed_back/"+jobId, "back")));
        body.add(new Item<>(new Button("button-form", "/estimating_worst_case_copula/"+jobId, "connect")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}