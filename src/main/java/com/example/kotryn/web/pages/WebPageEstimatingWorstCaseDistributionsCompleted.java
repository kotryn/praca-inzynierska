package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WebPageEstimatingWorstCaseDistributionsCompleted {

    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageEstimatingWorstCaseDistributionsCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        //ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        //String formattedDuration = Tools.formatDuration(processDescriptor.getDuration());
        Job job = jobRepository.findOne(jobId);

        List<String> selectedWorstCaseDistributions = Optional.ofNullable(job.getWorstCaseDistributions()).orElse(Collections.singletonList("none"));
        List<String> previouslySelectedWorstCaseDistributions = Optional.ofNullable(job.getSelectedWorstCaseDistributions()).orElse(Collections.singletonList("none"));

        List<Item> itemList = new ArrayList<>();

        Item<Button> itemBtnBack = new Item<>(new Button("button-back", "/estimating_worst_case_distributions_completed_back/"+jobId, "back"));
        Item<Button> itemBtnNext = new Item<>(new Button("button-form", "/estimating_growth_stocks/"+jobId, "Submit"));
        Item<Button> itemBtnDelete = new Item<>(new Button("button", "/prompt_user", "Start page"));

        itemList.add(new Item<>(new Text("text", "Estimating worst case distributions completed successfully")));
        itemList.add(new Item<>(new Text("text", "Previously: " + previouslySelectedWorstCaseDistributions)));
        itemList.add(new Item<>( new Text("text", "Available: ")));

        itemList.add(new Item<>(new Checkbox("checkbox", selectedWorstCaseDistributions, selectedWorstCaseDistributions)));

        itemList.add(itemBtnBack);
        itemList.add(itemBtnNext);
        itemList.add(itemBtnDelete);

        return new Page(new Body(itemList));
    }
}