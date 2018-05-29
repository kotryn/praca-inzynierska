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

        List<String> selectedRobustPortfolio = Optional.ofNullable(job.getRobustPortfolio()).orElse(Collections.singletonList("none"));
        List<String> previouslySelectedRobustPortfolio = Optional.ofNullable(job.getSelectedRobustPortfolio()).orElse(Collections.singletonList("none"));

        List<Item> itemList = new ArrayList<>();

        itemList.add(new Item<>(new Text("text", "Building robust portfolio completed successful")));
        itemList.add(new Item<>(new Text("text", "Previously: " + previouslySelectedRobustPortfolio)));
        itemList.add(new Item<>( new Text("text", "Available: ")));

        itemList.add(new Item<>(new Checkbox("checkbox", selectedRobustPortfolio, selectedRobustPortfolio)));

        Item<Button> itemBtnSubmit = new Item<>(new Button("button-form", "/calculating_statistic/"+jobId, "submit"));
        Item<Button> itemBtnBack = new Item<>(new Button("button-back", "/building_robust_portfolio_in_progress_completed_back/"+jobId, "back"));
        Item<Button> itemBtnDelete = new Item<>(new Button("button", "/prompt_user", "Start page"));

        itemList.add(itemBtnBack);
        itemList.add(itemBtnSubmit);
        itemList.add(itemBtnDelete);

        return new Page(new Body(itemList));
    }
}
