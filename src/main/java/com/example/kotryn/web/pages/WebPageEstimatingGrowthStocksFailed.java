package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.List;

public class WebPageEstimatingGrowthStocksFailed {
    private final ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageEstimatingGrowthStocksFailed(Long jobId, ProcessDescriptorRepository processDescriptorRepository) {
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {//TODO: failed
        List<Item> body = new ArrayList<>();
        List<Item> navbar = new ArrayList<>();

        navbar.add(new Item<>(new Button("button-start-page", "/prompt_user", "Start page")));
        navbar.add(new Item<>(new Text("text-navbar", "Job ID: "+jobId)));

        body.add(new Item<>(new Text("text", "Estimating growth stocks failed")));

        return new Page(new Navbar(navbar), new Body(body));
    }
}
