package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.entity.Stock;
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

        Item<Button> itemBtnBack = new Item<>(new Button("button-back", "/", "back"));
        Item<Button> itemBtnNext = new Item<>(new Button("button-form", "/", "Submit"));
        Item<Button> itemBtnDelete = new Item<>(new Button("button-delete", "/jobs/"+jobId, "Start page"));
        Item<Text> itemText = new Item<>(new Text("text", "Calculating sample count completed successfully"));

        List<Item> itemList = new ArrayList<>();

        itemList.add(itemText);
        itemList.add(itemBtnBack);
        itemList.add(itemBtnNext);
        itemList.add(itemBtnDelete);

        return new Page(new Body(itemList));
    }
}