package com.kotryn.web.pages;

import com.kotryn.entity.Job;
import com.kotryn.json.*;
import com.kotryn.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingWorstCaseCopulaCompleted {
    private JobRepository jobRepository;
    private final Long jobId;

    public WebPageEstimatingWorstCaseCopulaCompleted(Long jobId, JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://kotryn.localtunnel.me/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Estimating worst case copula completed successful")));

        if(job.getCopulaType().equals("Clayton copula")){
            body.add(new Entity<>(new Text("text", "Theta parameter (θ): "+job.getTheta())));
        }
        else if(job.getCopulaType().equals("t copula")){
            body.add(new Entity<>(new Text("text", "Correlation matrix - number degrees of freedom: "+job.getCorrelationMatrix())));
        }

        body.add(new Entity<>(new Button("button-back", "http://kotryn.localtunnel.me/estimating_worst_case_copula_completed_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button", "http://kotryn.localtunnel.me/building_robust_portfolio_setup/"+jobId, "Next")));

        return new Page(new Header(header), new Body(body));
    }
}
