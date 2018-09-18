package com.kotryn.web.pages;

import com.kotryn.entity.Job;
import com.kotryn.json.*;
import com.kotryn.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageCalculatingSampleCountSetup {

    private final Long jobId;
    private JobRepository jobRepository;

    public WebPageCalculatingSampleCountSetup(JobRepository jobRepository, Long jobId) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://kotryn.localtunnel.me/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "In-sample and out-of-sample periods")));

        String startInSampleDate = job.getStartInSampleDate().isEmpty() ? "not set" : job.getStartInSampleDate();
        String endInSampleDate = job.getEndInSampleDate().isEmpty() ? "not set" : job.getEndInSampleDate();
        String startOutOfSampleDate = job.getStartOutOfSampleDate().isEmpty() ? "not set" : job.getStartOutOfSampleDate();
        String endOutOfSampleDate = job.getEndOutOfSampleDate().isEmpty() ? "not set" : job.getEndOutOfSampleDate();

        body.add(new Entity<>(new Text("text", "Supply in-sample periods")));
        body.add(new Entity<>(new Text("text", "Previous in-sample start date: " + startInSampleDate)));
        body.add(new Entity<>(new Text("text", "Previous in-sample end date: " + endInSampleDate)));
        body.add(new Entity<>(new Input("input", "date", new String[]{"startInSampleDate", "endInSampleDate"}, new String[]{"Enter Start Date:", "Enter end date:"})));

        body.add(new Entity<>(new Text("text", "Supply out-of-sample periods")));
        body.add(new Entity<>(new Text("text", "Previous out-of-sample start date: " + startOutOfSampleDate)));
        body.add(new Entity<>(new Text("text", "Previous out-of-sample end date: " + endOutOfSampleDate)));
        body.add(new Entity<>(new Input("input", "date", new String[]{"startOutOfSampleDate", "endOutOfSampleDate"}, new String[]{"Enter Start Date:", "Enter end date:"})));

        body.add(new Entity<>(new Text("text", "Previous periodicity (days): " + job.getPeriodicity())));
        body.add(new Entity<>(new Input("input", "integer", new String[]{"periodicity"}, new String[]{"Enter periodicity in days:"})));

        body.add(new Entity<>(new Button("button-back", "http://kotryn.localtunnel.me/calculating_sample_count_setup_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button-form", "http://kotryn.localtunnel.me/calculating_sample_count/"+jobId, "Next")));

        return new Page(new Header(header), new Body(body));
    }
}