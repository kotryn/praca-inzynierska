package com.example.kotryn.web.pages;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Job.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;

import java.util.Optional;

import static java.lang.System.exit;

public class WebPageObtainingPeriodOfAnalysis {

    private final JobRepository jobRepository;
    private final long jobId;
    private final MainController controller;

    public WebPageObtainingPeriodOfAnalysis(long jobId, MainController controller, JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
        this.controller = controller;
        //show();
    }

    public Page show() {
        /*view.println("Supply the period of analysis");
        Job job = jobRepository.getOne(jobId);
        Optional<String> previousStartDate = Optional.ofNullable(job.getStartDate());
        view.println("Previous start date: " + previousStartDate.orElse("not set"));
        view.println("Enter start date:");
        String startDate = view.nextLine();
        Optional<String> previousEndDate = Optional.ofNullable(job.getEndDate());
        view.println("Previous end date: " + previousEndDate.orElse("not set"));
        view.println("Enter end date:");
        String endDate = view.nextLine();
        view.println("1. Delete job (link)");
        view.println("2. Next (link)");
        view.println("3. Exit");
        switch (view.nextInt()) {
            case 1:
                controller.jobsDELETE(jobId);
                // once 200 is received for DELETE, browser connects:
                controller.deleteConfirmationGET(jobId);
                break;
            case 2:
                WebDataObtainingPeriodOfAnalysis webData = new WebDataObtainingPeriodOfAnalysis(jobId);
                webData.setStartDate(startDate);
                webData.setEndDate(endDate);
                controller.obtainingPeriodOfAnalysisPOST(webData);
                // once 201 is received for POST, browser connects:
                controller.jobsGET(jobId);
                break;
            case 3:
                exit(0);
                break;
            default:
                throw new RuntimeException("Undefined option");
        }*/



        Job job = jobRepository.getOne(jobId);

        Text text = new Text("text", "Supply the period of analysis");

        Text text1 = new Text("text", "Previous start date: " + job.getStartDate());
        Text text2 = new Text("text", "Previous end date: " + job.getEndDate());

        Button btnForm = new Button("button", "/job/"+job.getId(), "submit");
        Input inputForm = new Input(new String[]{"startDate", "endDate"}, new String[]{"Enter Start Date:", "Enter end date:"});
        Form form = new Form("form", inputForm, btnForm);

        Button btnBack = new Button("button-back", "/", "back");
        Button btnNext= new Button("button", "/period_of_analysis/"+job.getId(), "Next");
        Button btnDelete = new Button("button-delete", "/", "Delete");

        Item<Text> item = new Item<>(text);
        Item<Text> item2 = new Item<>(text1);
        Item<Text> item3 = new Item<>(text2);
        Item<Form> item4 = new Item<>(form);
        Item<Button> item5 = new Item<>(btnBack);
        Item<Button> item6 = new Item<>(btnNext);
        Item<Button> item7 = new Item<>(btnDelete);

        Body body = new Body(item, item2, item3, item4, item5, item6, item7);

        return new Page(body);
    }
}
