package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingGrowthStocksSetup {
    private JobRepository jobRepository;
    private final Long jobId;

    public WebPageEstimatingGrowthStocksSetup(JobRepository jobRepository, Long jobId) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/start_page", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "Estimating growth stocks setup")));

        if(job.getMaxNumberSector() != null){
            body.add(new Item<>(new Text("text", "Previously selected maximum number in sector: " + job.getMaxNumberSector())));
        }
        body.add(new Item<>(new Input("input-integer", new String[]{"maxNumberSector"}, new String[]{"Maximum number in sector:"})));

        if(job.getMaxNumberIndustry() != null){
            body.add(new Item<>(new Text("text", "Previously selected maximum number in industry: " + job.getMaxNumberIndustry())));
        }
        body.add(new Item<>(new Input("input-integer", new String[]{"maxNumberIndustry"}, new String[]{"Maximum number in industry:"})));

        if(job.getMaxCoefficient() != null){
            body.add(new Item<>(new Text("text", "Previously selected maximum correlation coefficient: " + job.getMaxCoefficient())));
        }
        body.add(new Item<>(new Input("input-integer", new String[]{"maxCoefficient"}, new String[]{"Maximum correlation coefficient:"})));

        body.add(new Item<>(new Button("button-back", "/estimating_growth_stocks_setup_back/"+jobId, "Back")));//TODO
        body.add(new Item<>(new Button("button-form", "/estimating_growth_stocks/"+jobId, "Next")));

        return new Page(new Navbar(navbar) ,new Body(body));
    }
}