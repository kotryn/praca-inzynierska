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

        List<String> selectedNonCorrelatedStocks = Optional.ofNullable(job.getNonCorrelatedStocks()).orElse(Collections.singletonList("none"));
        List<String> previouslySelectedNonCorrelatedStocks  = Optional.ofNullable(job.getSelectedNonCorrelatedStocks()).orElse(Collections.singletonList("none"));

        List<Item> itemList = new ArrayList<>();

        Item<Button> itemBtnConnect = new Item<>(new Button("button-form", "/estimating_worst_case_copula/"+jobId, "connect"));
        Item<Button> itemBtnBack = new Item<>(new Button("button-back", "/estimating_non_correlated_stocks_completed_back/"+jobId, "back"));
        Item<Button> itemBtnDelete = new Item<>(new Button("button", "/prompt_user", "Start page"));

        itemList.add(new Item<>(new Text("text", "Estimating non correlated stocks completed successful")));
        itemList.add(new Item<>(new Text("text", "Previously: " + previouslySelectedNonCorrelatedStocks)));
        itemList.add(new Item<>( new Text("text", "Available: ")));

        itemList.add(new Item<>(new Checkbox("checkbox", selectedNonCorrelatedStocks, selectedNonCorrelatedStocks)));

        itemList.add(itemBtnBack);
        itemList.add(itemBtnConnect);
        itemList.add(itemBtnDelete);

        return new Page(new Body(itemList));
    }
}