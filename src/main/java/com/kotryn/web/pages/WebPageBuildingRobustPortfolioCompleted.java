package com.kotryn.web.pages;

import com.kotryn.entity.Job;
import com.kotryn.json.*;
import com.kotryn.repository.JobRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WebPageBuildingRobustPortfolioCompleted {
    private JobRepository jobRepository;
    private final Long jobId;

    public WebPageBuildingRobustPortfolioCompleted(Long jobId, JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://kotryn.localtunnel.me/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

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
        statistic.add("Mean rate of return");
        statistic.add("VaR (0,05)");
        statistic.add("CVaR (0,05)");
        statistic.add("Maximum drawdown");


        List<String> value = new ArrayList<>();
        value.add("Value");
        value.add("4,7");
        value.add("-10,6");
        value.add("-12,5");
        value.add("-14,7");

        table2.add(statistic);
        table2.add(value);

        body.add(new Entity<>(new Table("table", table2)));



        body.add(new Entity<>(new Button("button-back", "http://kotryn.localtunnel.me/building_robust_portfolio_in_progress_completed_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button", "http://kotryn.localtunnel.me/calculating_statistic/"+jobId, "Next")));

        return new Page(new Header(header), new Body(body));
    }
}
