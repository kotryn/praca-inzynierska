package com.kotryn.web.pages;

import com.kotryn.entity.GrowthStockSector;
import com.kotryn.entity.Job;
import com.kotryn.json.*;
import com.kotryn.repository.JobRepository;

import java.util.*;

public class WebPageEstimatingGrowthStocksCompleted {

    private JobRepository jobRepository;
    private final Long jobId;

    public WebPageEstimatingGrowthStocksCompleted(Long jobId, JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://localhost:8080/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));
        body.add(new Entity<>(new Text("text", "Estimating growth stocks completed successful")));

        Map<String, GrowthStockSector> map = new HashMap<>(job.getGrowthStock());

        for (Map.Entry<String, GrowthStockSector> entry : map.entrySet()) {
            String sector = entry.getKey();
            Set<String> industry = new HashSet<>();

            for (Map.Entry<String, String> element : entry.getValue().getIndustriesStocks().entrySet()) {
                industry.add(element.getValue());
            }
            List<Entity> dropdown = new ArrayList<>();

            for (String i : industry) {
                List<String> name = job.getAllKeysForValue(entry.getValue().getIndustriesStocks(), i);
                List<Entity> nameList = new ArrayList<>();
                nameList.add(new Entity<>(new StaticList("list", name)));
                dropdown.add(new Entity<>(new Dropdown("dropdown", i, nameList)));

            }
            body.add(new Entity<>(new Dropdown("dropdown", sector, dropdown)));
        }



        body.add(new Entity<>(new Button("button-back", "http://localhost:8080/estimating_growth_stocks_completed_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button", "http://localhost:8080/estimating_worst_case_copula_setup/"+jobId, "Next")));

        return new Page(new Header(header), new Body(body));
    }
}