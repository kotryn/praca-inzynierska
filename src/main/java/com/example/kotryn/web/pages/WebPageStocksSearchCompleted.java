package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.entity.Stock;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.lib.Tools;

import java.util.*;

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

        List<String> previouslySelectedStocks = Optional.ofNullable(job.getSelectedStocks()).orElse(Collections.singletonList("none"));

        List<Item> itemList = new ArrayList<>();

        Text text = new Text("text", "Searching for stocks completed successfully");
        Text text2 = new Text("text", "Elapsed time: "+formattedDuration);
        Text text3 = new Text("text", "Available stocks: ");
        Text text4 = new Text("text", "Previously selected stocks: " + previouslySelectedStocks);

        Button btnBack = new Button("button-back", "/jobsPOST/"+jobId, "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");

        Item<Text> itemText = new Item<>(text);
        Item<Text> itemText2 = new Item<>(text2);
        Item<Text> itemText3 = new Item<>(text3);
        Item<Text> itemText4 = new Item<>(text4);

        System.out.println(job.getStocks());
        Map<String, Stock> map = new HashMap<>(job.getStocks());

        itemList.add(itemText);
        itemList.add(itemText2);
        itemList.add(itemText3);

        for (Map.Entry<String, Stock> entry : map.entrySet()){
            itemList.add(new Item<>(new Text("text", entry.getKey())));
            itemList.add(new Item<>(new Checkbox("checkbox", entry.getValue().getStocks(), entry.getValue().getStocks())));
        }

        Item<Button> itemBtnBack = new Item<>(btnBack);
        Item<Button> itemBtnDelete = new Item<>(btnDelete);

        itemList.add(itemText4);
        itemList.add(itemBtnBack);
        itemList.add(itemBtnDelete);

        Body body = new Body(itemList);

        return new Page(body);
    }
}
