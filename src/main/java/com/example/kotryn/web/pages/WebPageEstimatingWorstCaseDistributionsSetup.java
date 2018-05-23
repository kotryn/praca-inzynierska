package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;

public class WebPageEstimatingWorstCaseDistributionsSetup {
    //private final JobRepository jobRepository;
    private final Long jobId;

    public WebPageEstimatingWorstCaseDistributionsSetup(Long jobId/*, JobRepository jobRepository*/) {
        //this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        //Job job = jobRepository.getOne(jobId);

        Text text = new Text("text", "Estimating worst case distributions setup");

        Button btnBack = new Button("button-back", "/estimating_worst_case_distributions_setup_back/"+jobId, "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");
        Button btnNext= new Button("button", "/estimating_worst_case_distributions/"+jobId, "Next");

        Item<Text> itemText = new Item<>(text);

        Item<Button> itemBtnBack = new Item<>(btnBack);
        Item<Button> itemBtnNext = new Item<>(btnNext);
        Item<Button> itemBtnDelete = new Item<>(btnDelete);

        return new Page(new Body(itemText, itemBtnBack, itemBtnNext, itemBtnDelete));
    }
}

