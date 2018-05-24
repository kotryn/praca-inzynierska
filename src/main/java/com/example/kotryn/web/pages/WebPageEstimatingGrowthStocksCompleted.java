package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WebPageEstimatingGrowthStocksCompleted {

    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageEstimatingGrowthStocksCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<String> selectedGrowthStocks = Optional.ofNullable(job.getGrowthStocks()).orElse(Collections.singletonList("none"));
        List<String> previouslySelectedGrowthStocks = Optional.ofNullable(job.getSelectedGrowthStocks()).orElse(Collections.singletonList("none"));

        List<Item> itemList = new ArrayList<>();

        Item<Button> itemBtnConnect = new Item<>(new Button("button-form", "/estimating_non_correlated_stocks/"+jobId, "Submit"));
        Item<Button> itemBtnBack = new Item<>(new Button("button-back", "/estimating_growth_stocks_completed_back/"+jobId, "back"));
        Item<Button> itemBtnDelete = new Item<>(new Button("button-delete", "/jobs/"+jobId, "Start page"));

        itemList.add(new Item<>(new Text("text", "Estimating growth stocks completed successful")));
        itemList.add(new Item<>(new Text("text", "Previously: " + previouslySelectedGrowthStocks)));
        itemList.add(new Item<>( new Text("text", "Available: ")));

        itemList.add(new Item<>(new Checkbox("checkbox", selectedGrowthStocks, selectedGrowthStocks)));

        itemList.add(itemBtnBack);
        itemList.add(itemBtnConnect);
        itemList.add(itemBtnDelete);

        return new Page(new Body(itemList));
    }
}