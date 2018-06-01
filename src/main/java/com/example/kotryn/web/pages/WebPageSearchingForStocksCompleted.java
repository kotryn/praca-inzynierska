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

    static <K, V> List<K> getAllKeysForValue(Map<K, V> mapOfWords, V value)
    {
        List<K> listOfKeys = null;

        //Check if Map contains the given value
        if(mapOfWords.containsValue(value))
        {
            // Create an Empty List
            listOfKeys = new ArrayList<>();

            // Iterate over each entry of map using entrySet
            for (Map.Entry<K, V> entry : mapOfWords.entrySet())
            {
                // Check if value matches with given value
                if (entry.getValue().equals(value))
                {
                    // Store the key from entry to the list
                    listOfKeys.add(entry.getKey());
                }
            }
        }
        // Return the list of keys whose value matches with given value.
        return listOfKeys;
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
        body.add(new Item<>(new Text("text", "Previously selected stocks: " + previouslySelectedStocks)));


        body.add(new Item<>(new Text("text", "Available stocks: ")));

        Map<String, Stock> map = new HashMap<>(job.getStocks());

        for (Map.Entry<String, Stock> entry : map.entrySet()){
            body.add(new Item<>(new Text("text", entry.getKey())));//Sector
            Set<String> industry = new HashSet<>();

            for(Map.Entry<String, String> element : entry.getValue().getIndustriesStocks().entrySet()){
                industry.add(element.getValue());
            }

            for(String s: industry){
                body.add(new Item<>(new Text("text", s)));//Industry
                List<String> name = getAllKeysForValue(entry.getValue().getIndustriesStocks(), s);
                body.add(new Item<>(new Checkbox("checkbox", name, name)));//Stocks
            }
        }


        body.add(new Item<>(new Button("button-back", "/jobsPOST/"+jobId, "back")));
        body.add(new Item<>(new Button("button-form", "/calculating_sample_count_setup/"+jobId, "Submit")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
