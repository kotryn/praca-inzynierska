package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.lib.Tools;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WebPageStocksSearchCompleted {

    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageStocksSearchCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        String formattedDuration = Tools.formatDuration(processDescriptor.getDuration());
        Job job = jobRepository.findOne(jobId);

        List<String> availableStocks = Optional.ofNullable(job.getAvailableStocks()).orElse(Collections.singletonList("none"));

        List<String> previouslySelectedStocks = Optional.ofNullable(job.getSelectedStocks()).orElse(Collections.singletonList("none"));

        Text text = new Text("text", "Searching for stocks completed successfully");
        Text text2 = new Text("text", "Elapsed time: "+formattedDuration);
        Text text3 = new Text("text", "Available stocks: ");//CHECKBOX
        Text text4 = new Text("text", "Previously selected stocks: " + previouslySelectedStocks);

        Checkbox checkbox = new Checkbox("checkbox", availableStocks, availableStocks);

        //Button btnBack = new Button("button-back", "/", "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");
        Item<Text> item = new Item<>(text);
        Item<Text> itemt2 = new Item<>(text2);
        Item<Text> itemt3 = new Item<>(text3);
        Item<Text> itemt4 = new Item<>(text4);
        Item<Checkbox> checkboxItem = new Item<>(checkbox);
        Item<Button> item3 = new Item<>(btnDelete);
        Body body = new Body(item, itemt2, itemt3, checkboxItem, itemt4, item3);

        return new Page(body);
    }
}
