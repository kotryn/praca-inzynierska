package com.example.kotryn.web.pages;

import com.example.kotryn.entity.Job;
import com.example.kotryn.json.*;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WebPageBuildingRobustPortfolioCompleted {
    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public WebPageBuildingRobustPortfolioCompleted(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://localhost:8080/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        //List<String> share = Optional.ofNullable(job.getPortfolioShare()).orElse(Collections.singletonList("none"));

        body.add(new Entity<>(new Text("text", "Portfolio optimization completed successful")));

        body.add(new Entity<>(new Text("text", "Composition of the optimal portfolio")));


        List<List<String>> list = new ArrayList<>();

        List<String> company = new ArrayList<>();
        company.add("Company");
        company.addAll(Optional.ofNullable(job.getPortfolioCompany()).orElse(Collections.singletonList("none")));

        List<String> share = new ArrayList<>();
        share.add("Share (%)");
        share.addAll(Optional.ofNullable(job.getPortfolioShare()).orElse(Collections.singletonList("none")));

        list.add(company);
        list.add(share);

        body.add(new Entity<>(new Table("table", list)));


        List<List<String>> table2 = new ArrayList<>();

        List<String> statistic = new ArrayList<>();
        statistic.add("Portfolio statistic");
        //statistic.add("Annual growth");
        statistic.add("Mean rate of return");
        //statistic.add("Standard deviation");
        //statistic.add("Semi-standard deviation");
        statistic.add("VaR (0,05)");
        statistic.add("CVaR (0,05)");
        //statistic.add("Sharpe ratio");
        //statistic.add("Sortino ratio");
        //statistic.add("Omega ratio");
        statistic.add("Maximum drawdown");
        //statistic.add("Maximum drawdown duration");
        //statistic.add("Calmar ratio");


        List<String> value = new ArrayList<>();
        value.add("Value");
        //value.add("-");
        value.add("4,7");
        //value.add("-");
        //value.add("-");
        value.add("-10,6");
        value.add("-12,5");
        //value.add("-");
        //value.add("-");
        //value.add("-");
        value.add("-14,7");
        //value.add("-");
        //value.add("-");

        table2.add(statistic);
        table2.add(value);

        body.add(new Entity<>(new Table("table", table2)));



        body.add(new Entity<>(new Button("button-back", "http://localhost:8080/building_robust_portfolio_in_progress_completed_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button", "http://localhost:8080/calculating_statistic/"+jobId, "Next")));

        return new Page(new Header(header), new Body(body));
    }
}
