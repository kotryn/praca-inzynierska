package com.example.kotryn.web.pages;

import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.json.*;
import com.example.kotryn.lib.Tools;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageCalculatingSampleCountFailed {

    private final ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageCalculatingSampleCountFailed(Long jobId, ProcessDescriptorRepository processDescriptorRepository) {
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {//TODO: failed
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        String formattedDuration = Tools.formatDuration(processDescriptor.getDuration());

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-start-page", "/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Calculating sample count failed. Reason: " + processDescriptor.getErrorMessage())));
        body.add(new Entity<>(new Text("text", "Elapsed time: "+formattedDuration)));
        body.add(new Entity<>(new Button("button-back", "/start_page", "Back")));

        return new Page(new Header(header), new Body(body));
    }
}
