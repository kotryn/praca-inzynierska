package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.entity.Sector;
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

        String previouslySelectedStocksText = "Previously selected stocks: " + previouslySelectedStocks;
        if(previouslySelectedStocks.size() <= 0){
            previouslySelectedStocksText = "Previously selected stocks: none";
        }

        List<Entity> header = new ArrayList<>();
        List<Entity> body = new ArrayList<>();

        header.add(new Entity<>(new Button("button-start-page", "/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Searching for stocks completed successfully")));
        body.add(new Entity<>(new Text("text", "Elapsed time: "+formattedDuration)));
        body.add(new Entity<>(new Text("text", previouslySelectedStocksText)));

        body.add(new Entity<>(new Text("text", "Available stocks: ")));

        Map<String, Sector> map = new HashMap<>(job.getStocks());

        for (Map.Entry<String, Sector> entry : map.entrySet()){
            String sector = entry.getKey();
            Set<String> industry = new HashSet<>();

            for(Map.Entry<String, String> element : entry.getValue().getIndustriesStocks().entrySet()){
                industry.add(element.getValue());
            }
            List<Entity> dropdown = new ArrayList<>();

            for(String i: industry){
                List<String> name = job.getAllKeysForValue(entry.getValue().getIndustriesStocks(), i);
                List<Entity> nameList = new ArrayList<>();
                nameList.add(new Entity<>(new Checkbox("checkbox", name, name)));
                dropdown.add(new Entity<>(new Dropdown("dropdown", i, nameList)));
            }
            body.add(new Entity<>(new Dropdown("dropdown", sector, dropdown)));
        }

        body.add(new Entity<>(new Button("button-back", "/jobsPOST/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button-form", "/calculating_sample_count_setup/"+jobId, "Next")));

        return new Page(new Header(header), new Body(body));
    }
}
