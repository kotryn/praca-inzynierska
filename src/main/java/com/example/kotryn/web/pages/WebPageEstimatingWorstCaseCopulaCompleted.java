package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WebPageEstimatingWorstCaseCopulaCompleted {
    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageEstimatingWorstCaseCopulaCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<String> selectedWorstCaseCopula = Optional.ofNullable(job.getWorstCaseCopula()).orElse(Collections.singletonList("none"));
        List<String> previouslySelectedWorstCaseCopula = Optional.ofNullable(job.getSelectedWorstCaseCopula()).orElse(Collections.singletonList("none"));

        List<Item> itemList = new ArrayList<>();

        Item<Button> itemBtnConnect = new Item<>(new Button("button-form", "/building_robust_portfolio/"+jobId, "connect"));
        Item<Button> itemBtnBack = new Item<>(new Button("button-back", "/estimating_worst_case_copula_in_progress_completed_back/"+jobId, "back"));
        Item<Button> itemBtnDelete = new Item<>(new Button("button-delete", "/jobs/"+jobId, "Start page"));

        itemList.add(new Item<>(new Text("text", "Estimating worst case copula completed successful")));
        itemList.add(new Item<>(new Text("text", "Previously: " + previouslySelectedWorstCaseCopula)));
        itemList.add(new Item<>( new Text("text", "Available: ")));

        itemList.add(new Item<>(new Checkbox("checkbox", selectedWorstCaseCopula, selectedWorstCaseCopula)));

        itemList.add(itemBtnBack);
        itemList.add(itemBtnConnect);
        itemList.add(itemBtnDelete);

        return new Page(new Body(itemList));
    }
}
