package com.example.kotryn.web.pages;

import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.json.*;
import com.example.kotryn.lib.Tools;
import com.example.kotryn.repository.ProcessDescriptorRepository;

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

        Text text = new Text("text", "Estimating worst case distributions failed. Reason: " + processDescriptor.getErrorMessage());
        Text text2 = new Text("text", "Elapsed time: "+formattedDuration);

        Button btnBack = new Button("button-back", "/", "back");
        Button btnDelete = new Button("button-delete", "/jobs/"+jobId, "Start page");

        Item<Text> itemText = new Item<>(text);
        Item<Text> itemText2 = new Item<>(text2);

        Item<Button> itemBtnDelete = new Item<>(btnDelete);
        Item<Button> itemBtnBack = new Item<>(btnBack);

        return new Page(new Body(itemText, itemText2, itemBtnBack, itemBtnDelete));
    }
}
