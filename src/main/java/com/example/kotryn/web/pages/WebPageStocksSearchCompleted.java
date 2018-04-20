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
        Text text3 = new Text("text", "Available stocks: ");
        Text text4 = new Text("text", "Previously selected stocks: " + previouslySelectedStocks);

        Checkbox checkbox = new Checkbox("checkbox", availableStocks, availableStocks);

        Button btnBack = new Button("button-back", "/jobsPOST/"+jobId, "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");
        Item<Text> itemText = new Item<>(text);
        Item<Text> itemText2 = new Item<>(text2);
        Item<Text> itemText3 = new Item<>(text3);
        Item<Text> itemText4 = new Item<>(text4);
        Item<Checkbox> itemCheckbox = new Item<>(checkbox);
        Item<Button> itemBtnBack = new Item<>(btnBack);
        Item<Button> itemBtnDelete = new Item<>(btnDelete);
        Body body = new Body(itemText, itemText2, itemText3, itemCheckbox, itemText4, itemBtnBack, itemBtnDelete);

        return new Page(body);
    }
}
