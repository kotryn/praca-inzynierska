package com.kotryn.web.pages;

import com.kotryn.entity.ProcessDescriptor;
import com.kotryn.json.*;
import com.kotryn.lib.Tools;
import com.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingWorstCaseDistributionsFailed {

    private final ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageEstimatingWorstCaseDistributionsFailed(Long jobId, ProcessDescriptorRepository processDescriptorRepository) {
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        String formattedDuration = Tools.formatDuration(processDescriptor.getDuration());

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://localhost:8080/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Estimating worst case distributions failed. Reason: " + processDescriptor.getErrorMessage())));

        body.add(new Entity<>(new Button("button-back", "http://localhost:8080/estimating_worst_case_distributions_failed_back/"+jobId, "Back")));

        return new Page(new Header(header), new Body(body));
    }
}
