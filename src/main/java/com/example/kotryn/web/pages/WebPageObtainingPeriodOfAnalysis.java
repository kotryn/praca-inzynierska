package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;

public class WebPageObtainingPeriodOfAnalysis {

    private final JobRepository jobRepository;
    private final Long jobId;

    public WebPageObtainingPeriodOfAnalysis(Long jobId, JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.getOne(jobId);

        Text text = new Text("text", "Supply the period of analysis");

        Text text1 = new Text("text", "Previous start date: " + job.getStartDate());
        Text text2 = new Text("text", "Previous end date: " + job.getEndDate());

        Button btnForm = new Button("button", "/jobSetDate/"+jobId, "submit");
        Input inputForm = new Input(new String[]{"startDate", "endDate"}, new String[]{"Enter Start Date:", "Enter end date:"});
        Form form = new Form("form", inputForm, btnForm);

        Button btnBack = new Button("button-back", "/", "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");
        Button btnNext= new Button("button", "/period_of_analysis/"+jobId, "Next");

        Item<Text> item = new Item<>(text);
        Item<Text> item2 = new Item<>(text1);
        Item<Text> item3 = new Item<>(text2);
        Item<Form> item4 = new Item<>(form);
        Item<Button> item5 = new Item<>(btnBack);
        Item<Button> item6 = new Item<>(btnNext);
        Item<Button> item7 = new Item<>(btnDelete);

        Body body = new Body(item, item2, item3, item4, item5, item6, item7);

        return new Page(body);
    }
}
