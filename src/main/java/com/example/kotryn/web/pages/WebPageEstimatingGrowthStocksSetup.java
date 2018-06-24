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

        List<Entity> body = new ArrayList<>();
        List<Entity> navbar = new ArrayList<>();

        navbar.add(new Entity<>(new Button("button-start-page", "/start_page", "Start page")));
        navbar.add(new Entity<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Entity<>(new Title("title", "h2","Estimating growth stocks setup")));

        if(job.getMaxNumberSector() != null){
            body.add(new Entity<>(new Text("text", "Previously selected maximum number in sector: " + job.getMaxNumberSector())));
        }
        body.add(new Entity<>(new Input("input-integer", new String[]{"maxNumberSector"}, new String[]{"Maximum number in sector:"})));

        if(job.getMaxNumberIndustry() != null){
            body.add(new Entity<>(new Text("text", "Previously selected maximum number in industry: " + job.getMaxNumberIndustry())));
        }
        body.add(new Entity<>(new Input("input-integer", new String[]{"maxNumberIndustry"}, new String[]{"Maximum number in industry:"})));

        if(job.getMaxCoefficient() != null){
            body.add(new Entity<>(new Text("text", "Previously selected maximum correlation coefficient: " + job.getMaxCoefficient())));
        }
        body.add(new Entity<>(new Input("input-integer", new String[]{"maxCoefficient"}, new String[]{"Maximum correlation coefficient:"})));

        body.add(new Entity<>(new Button("button-back", "/estimating_growth_stocks_setup_back/"+jobId, "Back")));//TODO
        body.add(new Entity<>(new Button("button-form", "/estimating_growth_stocks/"+jobId, "Next")));

        return new Page(new Navbar(navbar) ,new Body(body));
    }
}