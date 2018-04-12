package com.example.kotryn.web.pages;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Job.Job;
import com.example.kotryn.entity.Process.ProcessDescriptor;
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
    private MainController controller;

    public WebPageStocksSearchCompleted(Long jobId, MainController controller, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
        this.controller = controller;
    }

    public Page show() {
        /*ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        view.println("Searching for stocks completed successfully");
        String formattedDuration = Tools.formatDuration(processDescriptor.getDuration());
        view.println("Elapsed time: " + formattedDuration);
        Job job = jobRepository.getOne(jobId);
        List<String> availableStocks = Optional.ofNullable(job.getAvailableStocks())
                .orElse(Collections.singletonList("none"));
        view.println("Available stocks: " + availableStocks);
        List<String> previouslySelectedStocks = Optional.ofNullable(job.getSelectedStocks())
                .orElse(Collections.singletonList("none"));
        view.println("Previously selected stocks: " + previouslySelectedStocks);
        List<String> selectedStocks = null;
        if (!availableStocks.isEmpty()) {
            view.println("Enter selected stocks (use space as a separator):");
            String input = view.nextLine();
            selectedStocks = Arrays.asList(input.split("\\s+"));
        }
        view.println("1. Previous (link)");
        view.println("2. Next (link)");
        view.println("3. Exit");
        WebDataSearchingForStocksCompleted webData = new WebDataSearchingForStocksCompleted(jobId);
        switch (view.nextInt()) {
            case 1:
                webData.setAction(Action.PREVIOUS);
                webData.setSelectedStocks(selectedStocks);
                controller.searchingForStocksCompletedPOST(webData);
                // once 201 is received for POST, browser connects:
                controller.jobsGET(jobId);
                break;
            case 2:
                webData.setAction(Action.NEXT);
                webData.setSelectedStocks(selectedStocks);
                controller.searchingForStocksCompletedPOST(webData);
                // once 201 is received for POST, browser connects:
                controller.jobsGET(jobId);
                break;
            case 3:
                exit(0);
                break;
            default:
                throw new RuntimeException("Undefined option");
        }*/
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        String formattedDuration = Tools.formatDuration(processDescriptor.getDuration());
        Job job = jobRepository.findOne(jobId);

        List<String> availableStocks = Optional.ofNullable(job.getAvailableStocks()).orElse(Collections.singletonList("none"));

        List<String> previouslySelectedStocks = Optional.ofNullable(job.getSelectedStocks()).orElse(Collections.singletonList("none"));

        List<String> selectedStocks = null;
        Item<Text> itemt5;
        if (!availableStocks.isEmpty()) {
            Text text5 = new Text("text", "Enter selected stocks (use space as a separator):"); //bedzie input
            itemt5 = new Item<>(text5);

            //String input = view.nextLine();
            //selectedStocks = Arrays.asList(input.split("\\s+"));
        }else{
            Text text5 = new Text("text", "No stocks available");
            itemt5 = new Item<>(text5);
        }


        Text text = new Text("text", "Searching for stocks completed successfully");
        Text text2 = new Text("text", "Elapsed time: "+formattedDuration);
        Text text3 = new Text("text", "Available stocks: " + availableStocks);
        Text text4 = new Text("text", "Previously selected stocks: " + previouslySelectedStocks);

        //Button refresh = new Button("button", "/stocks_search_in_progress/"+jobId, "refresh");
        Button btnBack = new Button("button-back", "/", "back");
        Item<Text> item = new Item<>(text);
        Item<Text> itemt2 = new Item<>(text2);
        Item<Text> itemt3 = new Item<>(text3);
        Item<Text> itemt4 = new Item<>(text4);
        //Item<Button> item2 = new Item<>(refresh);
        Item<Button> item3 = new Item<>(btnBack);
        Body body = new Body(item, itemt2, itemt3, itemt4, itemt5, item3);

        return new Page(body);
    }
}
