package com.kotryn.web.pages;

import com.kotryn.entity.Job;
import com.kotryn.json.*;
import com.kotryn.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageObtainingPeriodOfAnalysis {

    private final JobRepository jobRepository;
    private final Long jobId;

    public WebPageObtainingPeriodOfAnalysis(Long jobId, JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.getOne(jobId);

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://localhost:8080/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Title("title", "h3", "Supply the period of analysis")));
        body.add(new Entity<>(new Text("text", "Previous start date: " + job.getStartDate())));
        body.add(new Entity<>(new Text("text", "Previous end date: " + job.getEndDate())));
        body.add(new Entity<>(new Input("input", "date", new String[]{"startDate", "endDate"}, new String[]{"Enter Start Date:", "Enter end date:"})));
        body.add(new Entity<>(new Button("button-back", "http://localhost:8080/start_page", "Back")));
        body.add(new Entity<>(new Button("button-form", "http://localhost:8080/job_set_date/"+jobId, "Submit")));
        body.add(new Entity<>(new Button("button", "http://localhost:8080/period_of_analysis/"+jobId, "Next")));

        return new Page(new Header(header), new Body(body));
    }
}
