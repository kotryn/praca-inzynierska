package com.kotryn.web.pages;

import com.kotryn.entity.Job;
import com.kotryn.json.*;
import com.kotryn.repository.JobRepository;

import java.util.*;

public class WebPageCalculatingStatisticCompleted {
    private JobRepository jobRepository;
    private final Long jobId;

    public WebPageCalculatingStatisticCompleted(Long jobId, JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://localhost:8080/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        List<String> x = Optional.ofNullable(job.getX()).orElse(Collections.singletonList("none"));
        List<Double> y = Optional.ofNullable(job.getY()).orElse(Collections.singletonList(null));
        Map<String, Double> xy = new HashMap<>();

        for(int i = 0; i < x.size(); i++){
            xy.put(x.get(i), y.get(i));
        }
        List<Map<String,Double>> data = new ArrayList<>();
        data.add(xy);

        body.add(new Entity<>(new Text("text", "Producing out-of sample portfolio graph completed successful")));

        body.add(new Entity<>(new Graph("graph", new String[]{"robust portfolio"}, data)));

        body.add(new Entity<>(new Button("button-back", "/calculating_statistic_in_progress_completed_back/"+jobId, "Back")));

        return new Page(new Header(header), new Body(body));
    }
}