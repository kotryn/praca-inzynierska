package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;

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

        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "Portfolio optimisation setup")));

        //body.add(new Item<>(new Text("text", "Previously selected number of samples: " /*+ job.getMaxNumberSector()*/)));
       // body.add(new Item<>(new Input("input-integer", new String[]{"numberOfSamples"}, new String[]{"Number of samples:"})));

       // body.add(new Item<>(new Text("text", "Previously selected required year rate of return: " /*+ job.getMaxNumberIndustry()*/)));
        //body.add(new Item<>(new Input("input-integer", new String[]{"yearRateOfReturn"}, new String[]{"Required year rate of return:"})));

        //body.add(new Item<>(new Text("text", "Previously selected CVaR (Conditional Value At Risk) tolerance level (ß): "/* + job.getMaxCoefficient()*/)));
        //body.add(new Item<>(new Input("input-integer", new String[]{"toleranceLevel"}, new String[]{"CVaR tolerance level (ß):"})));

        //body.add(new Item<>(new Text("text", "Previously selected required maximum number of shares: " /*+ job.getMaxCoefficient()*/)));
        //body.add(new Item<>(new Input("input-integer", new String[]{"maxShare"}, new String[]{"CVaR tolerance level (ß):"})));

        body.add(new Item<>(new Button("button-back", "/building_robust_portfolio_setup_back/"+jobId, "Back")));//TODO
        body.add(new Item<>(new Button("button-form", "/building_robust_portfolio/"+jobId, "Next")));

        return new Page(new Navbar(navbar) ,new Body(body));
    }
}