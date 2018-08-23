package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.*;

public class WebPageCalculatingSampleCountCompleted {

    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageCalculatingSampleCountCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        //String formattedDuration = Tools.formatDuration(processDescriptor.getDuration());
        Job job = jobRepository.findOne(jobId);

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://Lenovo-320:8081//start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Calculating sample count completed successfully")));
        body.add(new Entity<>(new Text("text", "In-sample: "+job.getInSample())));
        body.add(new Entity<>( new Text("text", "Out-of-sample: "+job.getOutOfSample())));

        body.add(new Entity<>(new Button("button-back", "http://Lenovo-320:8081//calculating_sample_count_completed_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button", "http://Lenovo-320:8081//estimating_worst_case_distributions_setup/"+jobId, "Next")));

        return new Page(new Header(header), new Body(body));
    }
}