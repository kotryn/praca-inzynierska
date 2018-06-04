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

        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "Calculating sample count failed. Reason: " + processDescriptor.getErrorMessage())));
        body.add(new Item<>(new Text("text", "Elapsed time: "+formattedDuration)));
        body.add(new Item<>(new Button("button-back", "/", "Back")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
