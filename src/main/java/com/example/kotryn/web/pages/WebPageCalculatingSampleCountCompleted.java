package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.json.*;
import com.example.kotryn.lib.Tools;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.*;

public class WebPageCalculatingSampleCountCompleted {

    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageCalculatingSampleCountCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        String formattedDuration = Tools.formatDuration(processDescriptor.getDuration());
        Job job = jobRepository.findOne(jobId);

        List<String> selectedCalculatingSample = Optional.ofNullable(job.getCalculatingSample()).orElse(Collections.singletonList("none"));
        List<String> previouslySelectedCalculatingSample = Optional.ofNullable(job.getSelectedCalculatingSample()).orElse(Collections.singletonList("none"));

        List<Item> itemList = new ArrayList<>();

        Item<Button> itemBtnBack = new Item<>(new Button("button-back", "/calculating_sample_count_completed_back/"+jobId, "back"));
        Item<Button> itemBtnNext = new Item<>(new Button("button-form", "/estimating_worst_case_distributions_setup/"+jobId, "Submit"));
        Item<Button> itemBtnDelete = new Item<>(new Button("button-delete", "/jobs/"+jobId, "Start page"));



        itemList.add(new Item<>(new Text("text", "Calculating sample count completed successfully")));
        itemList.add(new Item<>(new Text("text", "Previously: " + previouslySelectedCalculatingSample)));
        itemList.add(new Item<>( new Text("text", "Available: ")));

        itemList.add(new Item<>(new Checkbox("checkbox", selectedCalculatingSample, selectedCalculatingSample)));

        itemList.add(itemBtnBack);
        itemList.add(itemBtnNext);
        itemList.add(itemBtnDelete);

        return new Page(new Body(itemList));
    }
}