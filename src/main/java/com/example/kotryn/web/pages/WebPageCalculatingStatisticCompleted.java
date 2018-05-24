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

        List<String> statistic = Optional.ofNullable(job.getStatistic()).orElse(Collections.singletonList("none"));

        List<Item> itemList = new ArrayList<>();

        Item<Button> itemBtnBack = new Item<>(new Button("button-back", "/calculating_statistic_in_progress_completed_back/"+jobId, "back"));
        Item<Button> itemBtnDelete = new Item<>(new Button("button-delete", "/jobs/"+jobId, "Start page"));

        itemList.add(new Item<>(new Text("text", "Calculating out-of sample statistic completed successful")));
        itemList.add(new Item<>( new Text("text", "Result: ")));

        itemList.add(new Item<>(new ListJ("list", statistic)));

        itemList.add(itemBtnBack);
        itemList.add(itemBtnDelete);

        return new Page(new Body(itemList));
    }
}