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

        navbar.add(new Item<>(new Button("button-start-page", "/start_page", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));
        body.add(new Item<>(new Text("text", "Estimating growth stocks completed successful")));

        Map<String, GrowthStockSector> map = new HashMap<>(job.getGrowthStock());

        for (Map.Entry<String, GrowthStockSector> entry : map.entrySet()) {
            String sector = entry.getKey();
            Set<String> industry = new HashSet<>();

            for (Map.Entry<String, String> element : entry.getValue().getIndustriesStocks().entrySet()) {
                industry.add(element.getValue());
            }
            List<Item> dropdown = new ArrayList<>();

            for (String i : industry) {
                List<String> name = job.getAllKeysForValue(entry.getValue().getIndustriesStocks(), i);
                List<Item> nameList = new ArrayList<>();
                nameList.add(new Item<>(new StaticList("list", name)));
                dropdown.add(new Item<>(new Dropdown("dropdown", i, nameList)));

            }
            body.add(new Item<>(new Dropdown("dropdown", sector, dropdown)));
        }



        body.add(new Item<>(new Button("button-back", "/estimating_growth_stocks_completed_back/"+jobId, "Back")));
        body.add(new Item<>(new Button("button", "/estimating_worst_case_copula_setup/"+jobId, "Next")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}