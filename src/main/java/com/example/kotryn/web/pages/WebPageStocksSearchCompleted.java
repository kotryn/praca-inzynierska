package com.example.kotryn.web.pages;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.json.Page;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

public class WebPageStocksSearchCompleted {

    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final long jobId;
    private MainController controller;

    public WebPageStocksSearchCompleted(long jobId, MainController controller, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
        this.controller = controller;
    }

    public Page show() {
        /*ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        view.println("Searching for stocks completed successfully");
        String formattedDuration = Tools.formatDuration(processDescriptor.getDuration());
        view.println("Elapsed time: " + formattedDuration);
        Job job = jobRepository.getOne(jobId);
        List<String> availableStocks = Optional.ofNullable(job.getAvailableStocks())
                .orElse(Collections.singletonList("none"));
        view.println("Available stocks: " + availableStocks);
        List<String> previouslySelectedStocks = Optional.ofNullable(job.getSelectedStocks())
                .orElse(Collections.singletonList("none"));
        view.println("Previously selected stocks: " + previouslySelectedStocks);
        List<String> selectedStocks = null;
        if (!availableStocks.isEmpty()) {
            view.println("Enter selected stocks (use space as a separator):");
            String input = view.nextLine();
            selectedStocks = Arrays.asList(input.split("\\s+"));
        }
        view.println("1. Previous (link)");
        view.println("2. Next (link)");
        view.println("3. Exit");
        WebDataSearchingForStocksCompleted webData = new WebDataSearchingForStocksCompleted(jobId);
        switch (view.nextInt()) {
            case 1:
                webData.setAction(Action.PREVIOUS);
                webData.setSelectedStocks(selectedStocks);
                controller.searchingForStocksCompletedPOST(webData);
                // once 201 is received for POST, browser connects:
                controller.jobsGET(jobId);
                break;
            case 2:
                webData.setAction(Action.NEXT);
                webData.setSelectedStocks(selectedStocks);
                controller.searchingForStocksCompletedPOST(webData);
                // once 201 is received for POST, browser connects:
                controller.jobsGET(jobId);
                break;
            case 3:
                exit(0);
                break;
            default:
                throw new RuntimeException("Undefined option");
        }*/

        return null;
    }
}
