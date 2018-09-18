package com.kotryn.web.pages;

import com.kotryn.entity.Job;
import com.kotryn.json.*;
import com.kotryn.repository.JobRepository;

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
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://kotryn.localtunnel.me/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Title("title", "h2","Estimating growth stocks setup")));

        if(job.getMaxNumberSector() != null){
            body.add(new Entity<>(new Text("text", "Previously selected maximum number in sector: " + job.getMaxNumberSector())));
        }
        body.add(new Entity<>(new Input("input", "number", new String[]{"maxNumberSector"}, new String[]{"Maximum number in sector:"})));

        if(job.getMaxNumberIndustry() != null){
            body.add(new Entity<>(new Text("text", "Previously selected maximum number in industry: " + job.getMaxNumberIndustry())));
        }
        body.add(new Entity<>(new Input("input", "number", new String[]{"maxNumberIndustry"}, new String[]{"Maximum number in industry:"})));

        if(job.getMaxCoefficient() != null){
            body.add(new Entity<>(new Text("text", "Previously selected maximum correlation coefficient: " + job.getMaxCoefficient())));
        }
        body.add(new Entity<>(new Input("input", "number", new String[]{"maxCoefficient"}, new String[]{"Maximum correlation coefficient:"})));

        body.add(new Entity<>(new Button("button-back", "/estimating_growth_stocks_setup_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button-form", "/estimating_growth_stocks/"+jobId, "Next")));

        return new Page(new Header(header) ,new Body(body));
    }
}