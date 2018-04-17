package com.example.kotryn.web.pages;

import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.json.*;
import com.example.kotryn.lib.Tools;
import com.example.kotryn.repository.ProcessDescriptorRepository;

public class WebPageStocksSearchFailed {

    private final ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageStocksSearchFailed(Long jobId, ProcessDescriptorRepository processDescriptorRepository) {
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        String formattedDuration = Tools.formatDuration(processDescriptor.getDuration());

        Text text = new Text("text", "Searching for stocks failed. Reason: " + processDescriptor.getErrorMessage());
        Text text2 = new Text("text", "Elapsed time: "+formattedDuration);

        //Button btnBack = new Button("button-back", "/", "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");
        Item<Text> item = new Item<>(text);
        Item<Text> item2 = new Item<>(text2);
        Item<Button> item3 = new Item<>(btnDelete);
        Body body = new Body(item, item2, item3);

        return new Page(body);
    }
}
