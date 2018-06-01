package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.json.*;
import com.example.kotryn.lib.Tools;
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
        //Job job = jobRepository.findOne(jobId);

        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        //List<String> selectedCalculatingSample = Optional.ofNullable(job.getCalculatingSample()).orElse(Collections.singletonList("none"));

        body.add(new Item<>(new Text("text", "Calculating sample count completed successfully")));
        body.add(new Item<>(new Text("text", "In-sample: 30")));//TODO: wyświetlać "policzone" sample
        body.add(new Item<>( new Text("text", "Out-of-sample: 10")));

        //body.add(new Item<>(new Checkbox("checkbox", selectedCalculatingSample, selectedCalculatingSample)));

        body.add(new Item<>(new Button("button-back", "/calculating_sample_count_completed_back/"+jobId, "back")));
        body.add(new Item<>(new Button("button", "/estimating_worst_case_distributions_setup/"+jobId, "Next")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}