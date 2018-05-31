package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.entity.Stock;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.lib.Tools;

import java.util.*;

public class WebPageSearchingForStocksCompleted {

    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageSearchingForStocksCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        String formattedDuration = Tools.formatDuration(processDescriptor.getDuration());
        Job job = jobRepository.findOne(jobId);

        List<String> previouslySelectedStocks = Optional.ofNullable(job.getSelectedStocks()).orElse(Collections.singletonList("none"));

        List<Item> navbar = new ArrayList<>();
        List<Item> body = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "Searching for stocks completed successfully")));
        body.add(new Item<>(new Text("text", "Elapsed time: "+formattedDuration)));
        body.add(new Item<>(new Text("text", "Available stocks: ")));

        Map<String, Stock> map = new HashMap<>(job.getStocks());

        for (Map.Entry<String, Stock> entry : map.entrySet()){
            body.add(new Item<>(new Text("text", entry.getKey())));
            List<String> name = new ArrayList<>();
            int id = 0;
            for (String element : entry.getValue().getCompanies()) {
                name.add(element+" ["+entry.getValue().getSymbols().get(id++)+"]");
            }
            body.add(new Item<>(new Checkbox("checkbox", entry.getValue().getSymbols(), name)));
        }


        body.add(new Item<>(new Text("text", "Previously selected stocks: " + previouslySelectedStocks)));
        body.add(new Item<>(new Button("button-back", "/jobsPOST/"+jobId, "back")));
        body.add(new Item<>(new Button("button-form", "/calculating_sample_count/"+jobId, "Submit")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
