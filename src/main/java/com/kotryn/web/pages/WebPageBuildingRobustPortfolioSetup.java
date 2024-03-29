package com.kotryn.web.pages;

import com.kotryn.entity.Job;
import com.kotryn.json.*;
import com.kotryn.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageBuildingRobustPortfolioSetup {
    private JobRepository jobRepository;
    private final Long jobId;

    public WebPageBuildingRobustPortfolioSetup(JobRepository jobRepository, Long jobId) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://localhost:8080/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Portfolio optimisation setup")));

        if(job.getNumberOfSamples() != null){
            body.add(new Entity<>(new Text("text", "Previously selected number of samples: " + job.getNumberOfSamples())));
        }
        body.add(new Entity<>(new Input("input", "number", new String[]{"numberOfSamples"}, new String[]{"Number of samples:"})));

        if(job.getYearRateOfReturn() != null){
            body.add(new Entity<>(new Text("text", "Previously selected required year rate of return: " + job.getYearRateOfReturn())));
        }
        body.add(new Entity<>(new Input("input", "number", new String[]{"yearRateOfReturn"}, new String[]{"Required year rate of return:"})));

        if(job.getToleranceLevel() != null){
            body.add(new Entity<>(new Text("text", "Previously selected CVaR (Conditional Value At Risk) tolerance level (ß): " + job.getToleranceLevel())));
        }
        body.add(new Entity<>(new Input("input", "number", new String[]{"toleranceLevel"}, new String[]{"CVaR tolerance level (ß):"})));

        if(job.getMaxShare() != null){
            body.add(new Entity<>(new Text("text", "Previously selected required maximum number of shares: " + job.getMaxShare())));
        }
        body.add(new Entity<>(new Input("input", "number", new String[]{"maxShare"}, new String[]{"Required maximum number of shares:"})));

        body.add(new Entity<>(new Button("button-back", "http://localhost:8080/building_robust_portfolio_setup_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button-form", "http://localhost:8080/building_robust_portfolio/"+jobId, "Next")));

        return new Page(new Header(header) ,new Body(body));
    }
}