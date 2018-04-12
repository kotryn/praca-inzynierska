package com.example.kotryn.web.pages;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.json.Page;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import static java.lang.System.exit;

public class WebPageStocksSearchFailed {

    private final ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;
    private final MainController controller;

    public WebPageStocksSearchFailed(Long jobId, MainController controller, ProcessDescriptorRepository processDescriptorRepository) {
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
        this.controller = controller;
    }

    public Page show() {
        /*ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        view.println("Searching for stocks failed. Reason: " + processDescriptor.getErrorMessage());
        String formattedDuration = Tools.formatDuration(processDescriptor.getDuration());
        view.println("Elapsed time: " + formattedDuration);
        view.println("1. Continue (link)");
        view.println("2. Exit");
        switch (view.nextInt()) {
            case 1:
                WebDataSearchingForStocksFailed webData = new WebDataSearchingForStocksFailed(jobId);
                controller.searchingForStocksFailedPOST(webData);
                // once 201 is received for POST, browser connects:
                controller.jobsGET(jobId);
                break;
            case 2:
                exit(0);
                break;
            default:
                throw new RuntimeException("Undefined option");
        }*/
        return null;
    }
}
