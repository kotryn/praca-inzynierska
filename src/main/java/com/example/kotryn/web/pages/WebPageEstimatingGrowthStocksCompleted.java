package com.example.kotryn.web.pages;

import com.example.kotryn.entity.GrowthStockSector;
import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.WorstCaseDistributionSector;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.*;

public class WebPageEstimatingGrowthStocksCompleted {

    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageEstimatingGrowthStocksCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));
        body.add(new Item<>(new Text("text", "Estimating growth stocks completed successful")));

        Map<String, GrowthStockSector> map = new HashMap<>(job.getGrowthStock());

        for (Map.Entry<String, GrowthStockSector> entry : map.entrySet()) {
            body.add(new Item<>(new Text("text", entry.getKey())));//Sector
            Set<String> industry = new HashSet<>();

            for (Map.Entry<String, String> element : entry.getValue().getIndustriesStocks().entrySet()) {
                industry.add(element.getValue());
            }

            for (String s : industry) {
                body.add(new Item<>(new Text("text", s)));//Industry
                List<String> name = job.getAllKeysForValue(entry.getValue().getIndustriesStocks(), s);
                body.add(new Item<>(new ListJ("list", name)));//Stocks
            }
        }

        body.add(new Item<>(new Button("button-back", "/estimating_growth_stocks_completed_back/"+jobId, "Back")));
        body.add(new Item<>(new Button("button", "/estimating_worst_case_copula_setup/"+jobId, "Submit")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}