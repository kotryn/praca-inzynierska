package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

public class WebPageEstimatingNonCorrelatedStocksCompleted {
    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageEstimatingNonCorrelatedStocksCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Text text = new Text("text", "Estimating non correlated stocks completed successful");

        Button btnConnect = new Button("button-form", "/estimating_worst_case_copula/"+jobId, "connect");
        Button btnBack = new Button("button-back", "/estimating_non_correlated_stocks_completed_back/"+jobId, "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");

        Item<Text> itemText = new Item<>(text);

        Item<Button> itemBtnConnect = new Item<>(btnConnect);
        Item<Button> itemBtnBack = new Item<>(btnBack);
        Item<Button> itemBtnDelete = new Item<>(btnDelete);

        return new Page(new Body(itemText, itemBtnBack, itemBtnConnect, itemBtnDelete));
    }
}