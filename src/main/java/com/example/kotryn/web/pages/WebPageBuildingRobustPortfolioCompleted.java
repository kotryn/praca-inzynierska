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

        navbar.add(new Item<>(new Button("button-start-page", "/start_page", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        //List<String> share = Optional.ofNullable(job.getPortfolioShare()).orElse(Collections.singletonList("none"));

        body.add(new Item<>(new Text("text", "Portfolio optimization completed successful")));

        body.add(new Item<>(new Text("text", "Composition of the optimal portfolio")));


        List<List<String>> list = new ArrayList<>();

        List<String> company = new ArrayList<>();
        company.add("Company");
        company.addAll(Optional.ofNullable(job.getPortfolioCompany()).orElse(Collections.singletonList("none")));
        List<String> share = new ArrayList<>();
        share.add("Share");
        share.addAll(Optional.ofNullable(job.getPortfolioShare()).orElse(Collections.singletonList("none")));

        list.add(company);
        list.add(share);

        //System.out.println(list.size());

        body.add(new Item<>(new Table("table", list)));




        body.add(new Item<>(new Button("button-back", "/building_robust_portfolio_in_progress_completed_back/"+jobId, "Back")));
        body.add(new Item<>(new Button("button-form", "/calculating_statistic/"+jobId, "Next")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
