package com.kotryn.web.pages;

import com.kotryn.entity.ProcessDescriptor;
import com.kotryn.json.*;
import com.kotryn.lib.Tools;
import com.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageSearchingForStocksFailed {

    private final ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageSearchingForStocksFailed(Long jobId, ProcessDescriptorRepository processDescriptorRepository) {
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        String formattedDuration = Tools.formatDuration(processDescriptor.getDuration());

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://kotryn.localtunnel.me/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Searching for stocks failed. Reason: " + processDescriptor.getErrorMessage())));

        body.add(new Entity<>(new Button("button-back", "http://kotryn.localtunnel.me/jobsPOST/"+jobId, "Back")));

        return new Page(new Header(header), new Body(body));
    }
}
