package com.kotryn.web.pages;

import com.kotryn.entity.Job;
import com.kotryn.entity.WorstCaseDistributionSector;
import com.kotryn.json.*;
import com.kotryn.repository.JobRepository;

import java.util.*;

public class WebPageEstimatingWorstCaseDistributionsCompleted {

    private JobRepository jobRepository;
    private final Long jobId;

    public WebPageEstimatingWorstCaseDistributionsCompleted(Long jobId, JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://localhost:8080/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Title("title", "h2", "Estimating worst case distributions completed successfully")));

        List<Entity> dropdown = new ArrayList<>();
        Map<String, WorstCaseDistributionSector> map = new HashMap<>(job.getWorstCaseDistributionStocks());

        for (Map.Entry<String, WorstCaseDistributionSector> entry : map.entrySet()) {
            dropdown.add(new Entity<>(new Title("title", "h3-left", entry.getKey())));
            Set<String> industry = new HashSet<>();

            for (Map.Entry<String, String> element : entry.getValue().getIndustriesStocks().entrySet()) {
                industry.add(element.getValue());
            }

            for (String i : industry) {
                dropdown.add(new Entity<>(new Title("title", "h4-left", i)));//Industry
                List<String> name = job.getAllKeysForValue(entry.getValue().getIndustriesStocks(), i);
                dropdown.add(new Entity<>(new StaticList("list", name)));//Stocks
            }
        }

        body.add(new Entity<>(new Dropdown("dropdown", "Results", dropdown)));

        body.add(new Entity<>(new Button("button-back", "/estimating_worst_case_distributions_completed_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button", "/estimating_growth_stocks_setup/"+jobId, "Next")));

        return new Page(new Header(header), new Body(body));
    }
}