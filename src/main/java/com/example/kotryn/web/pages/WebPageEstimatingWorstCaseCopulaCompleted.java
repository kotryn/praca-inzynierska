package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingWorstCaseCopulaCompleted {
    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageEstimatingWorstCaseCopulaCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
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

        body.add(new Item<>(new Text("text", "Estimating worst case copula completed successful")));

        if(job.getCopulaType().equals("Clayton copula")){
            body.add(new Item<>(new Text("text", "Theta parameter (θ): "+job.getTheta())));
        }
        else if(job.getCopulaType().equals("t copula")){
            body.add(new Item<>(new Text("text", "Correlation matrix - number degrees of freedom: "+job.getCorrelationMatrix())));
        }

        body.add(new Item<>(new Button("button-back", "/estimating_worst_case_copula_completed_back/"+jobId, "Back")));
        body.add(new Item<>(new Button("button", "/building_robust_portfolio_setup/"+jobId, "Next")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
