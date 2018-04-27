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
        Text textStartDate = new Text("text", "Previous start date: " + job.getStartDate());
        Text textEndDate = new Text("text", "Previous end date: " + job.getEndDate());

        Input input = new Input("input", new String[]{"startDate", "endDate"}, new String[]{"Enter Start Date:", "Enter end date:"});

        Button btnBack = new Button("button-back", "/", "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");
        Button btnNext= new Button("button", "/period_of_analysis/"+jobId, "Next");
        Button btnForm = new Button("button-form", "/jobSetDate/"+jobId, "submit");

        Item<Text> itemText = new Item<>(text);
        Item<Text> itemTextStartDate = new Item<>(textStartDate);
        Item<Text> itemTextEndDate = new Item<>(textEndDate);
        Item<Input> itemForm = new Item<>(input);

        Item<Button> itemBtnBack = new Item<>(btnBack);
        Item<Button> itemBtnNext = new Item<>(btnNext);
        Item<Button> itemBtnDelete = new Item<>(btnDelete);
        Item<Button> itemBtnForm = new Item<>(btnForm);

        return new Page(new Body(itemText, itemTextStartDate, itemTextEndDate, itemForm, itemBtnForm, itemBtnBack, itemBtnNext, itemBtnDelete));
    }
}
